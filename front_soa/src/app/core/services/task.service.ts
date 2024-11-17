import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from '../models/task.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiUrl = 'http://localhost:8080/api/tasks'; // URL del backend

  constructor(private http: HttpClient) { }

  // Obtener todos los Users
  getUsers(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl);
  }

  // Crear un nuevo User
  createUser(Task: Task): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, Task);
  }

  // Actualizar un User existente
  updateUser(id: number, Task: Task): Observable<Task> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<Task>(url, Task);
  }

  // Eliminar un User por su ID
  deleteUser(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
