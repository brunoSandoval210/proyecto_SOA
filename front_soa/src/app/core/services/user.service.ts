import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = `${environment.apiUrl}`;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }
  getToken() {
    return this.authService.token;
  }

  searchUserByEmail(email: string): Observable<any> {
    const url = `${this.url}/user/emailLike`;
    return this.http.get(url, {
      headers: this.getAuthHeaders(),
      params: { email },
    });
  }


  // Obtener todos los Users
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  // Obtener user
  getUserbyId(userId: number): Observable<User> {
    return this.http.get<User>(`${this.url}/user/${userId}`);
  }

  // Crear un nuevo User
  createUser(User: any): Observable<User> {
    return this.http.post<any>(`${this.url}/registerUser`, User);
  }

  // Actualizar un User existente
  updateUser(id: number, User: User): Observable<User> {
    const url = `${this.url}/${id}`;
    return this.http.put<User>(url, User);
  }

  // Eliminar un User por su ID
  deleteUser(id: number): Observable<void> {
    const url = `${this.url}/${id}`;
    return this.http.delete<void>(url);
  }
}
