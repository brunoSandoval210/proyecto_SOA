import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userSubject = new BehaviorSubject<any>(null);
  user$ = this.userSubject.asObservable();

  private url: string = `${environment.apiUrl}/auth`;
  private _token!: string | undefined;
  private _user: any = {
    role: '',
    id: 0,
    username: '',
    isAuth: false,
  };

  constructor(private http: HttpClient, private router: Router) {}

  private isBrowser(): boolean {
    return typeof window !== 'undefined';
  }

  loginUser({ username, password }: any): Observable<any> {
    return this.http.post<any>(`${this.url}/login`, { username, password }).pipe(
      tap(response => {
        if (response && response.token) {
          this.token = response.token;
        }
      })
    );
  }

  sendEmailForRecoveryPassword(email: string): Observable<any> {
    return this.http.post<any>(`${this.url}/recuperarContrasena/${email}`, {});
  }

  changePassword(password: string, validPassword: string, code: string): Observable<any> {
    const body = { password, validPassword, code };
    return this.http.post<any>(`${this.url}/cambiarContrasena`, body);
  }

  getAuthHeaders(): HttpHeaders {
    const token = this.token;
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  set user(user: any) {
    this._user = user;
    this._user.isAuth = true;
    if (this.isBrowser()) {
      sessionStorage.setItem('login', JSON.stringify(user));
    }
    this.userSubject.next(this._user); // Notifica a los suscriptores
  }

  get user() {
    if (this._user.isAuth) {
      return this._user;
    } else if (this.isBrowser() && sessionStorage.getItem('login') != null) {
      this._user = JSON.parse(sessionStorage.getItem('login') || '{}');
      return this._user;
    }
    return this._user;
  }

  set token(token: string) {
    this._token = token;
    if (this.isBrowser()) {
      sessionStorage.setItem('token', token);
    }
    // Extraer información del usuario del token
    const payload = this.getPayload(token);
    if (payload) {
      this.user = {
        id: payload.id, // Asegúrate de que el payload tenga esta propiedad
        username: payload.username, // Asegúrate de que el payload tenga esta propiedad
        role: payload.authorities ? payload.authorities[0] : '', // Verifica si authorities está definido
        isAuth: true,
      };
      if (this.isBrowser()) {
        sessionStorage.setItem('userId', payload.id);
      }
      console.log('Token válido hasta:', new Date(payload.exp * 1000));
    }
  }

  get token() {
    if (this._token !== undefined) {
      return this._token;
    } else if (this.isBrowser() && sessionStorage.getItem('token') != null) {
      this._token = sessionStorage.getItem('token') || '';
      return this._token;
    }
    return this._token!;
  }

  getUserId() {
    if (this._user.id) {
      return this._user.id;
    } else if (this.isBrowser() && sessionStorage.getItem('userId') != null) {
      this._user.id = parseInt(sessionStorage.getItem('userId') || '0', 10);
      return this._user.id;
    }
    return 0;
  }

  getPayload(token: string) {
    if (token !== undefined) {
      try {
        return JSON.parse(atob(token.split('.')[1])); // Decodificar el payload del token
      } catch (e) {
        console.error('Error decodificando el token:', e);
        return null;
      }
    }
    return null;
  }

  isAuthenticated(): boolean {
    const token = this.token;
    if (token) {
      const payload = this.getPayload(token);
      if (payload) {
        const currentTime = Math.floor(Date.now() / 1000);
        return payload.exp > currentTime;
      }
    }
    return false;
  }

  logout() {
    this._token = undefined;
    this._user = {
      isAuth: false,
      role: '',
      id: 0,
      username: '',
    };
    if (this.isBrowser()) {
      sessionStorage.removeItem('login');
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('userId');
    }
    this.router.navigate(['/login']);
  }
}