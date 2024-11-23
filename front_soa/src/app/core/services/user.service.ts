import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/users'; // URL del backend

  constructor(private http: HttpClient) { }

  // Obtener todos los Users
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  // Crear un nuevo User
  createUser(User: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, User);
  }

  // Actualizar un User existente
  updateUser(id: number, User: User): Observable<User> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<User>(url, User);
  }

  // Eliminar un User por su ID
  deleteUser(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
