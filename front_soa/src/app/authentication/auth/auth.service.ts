import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private tokenKey = 'authToken';

  constructor(private router: Router) {}

  // Almacena el token en localStorage
  setToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  // Obtiene el token de localStorage
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Verifica si el usuario está autenticado
  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  // Cierra sesión eliminando el token
  logout() {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/login']);  // Redirigir al login después del logout
  }
}
