import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { response } from 'express';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private url: string = "http://localhost:8080/auth";
  private token!: string | undefined;

  constructor(private http: HttpClient) {}

  login({ username, password }: any): Observable<any> {
    return this.http.post(this.url + "/login", { username, password });
  }

  getUser(): any {
    if (this.token) {
      const payload = JSON.parse(atob(this.token.split(".")[1]));
      return payload.sub;
    }
    return null;
  }

  // Establece el token en memoria y Local Storage
  setToken(token: string): void {
    this.token = token;
    localStorage.setItem("authToken", token); // Clave fija para Local Storage
  }

  // Obtiene el token desde Local Storage
  getToken(): string | undefined {
    if (!this.token) {
      this.token = localStorage.getItem("authToken") || undefined; // Sincroniza con Local Storage
    }
    return this.token;
  }

  // Elimina el token de memoria y Local Storage
  removeToken(): void {
    this.token = undefined;
    localStorage.removeItem("authToken");
  }

  isAuth(): boolean {
    return this.token !== undefined && this.token !== null;
  }

  // Verifica si el usuario está autenticado
  isAuthenticated(): boolean {
    return this.getToken() !== null; // Comprueba si hay un token en Local Storage
  }

}
