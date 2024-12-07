import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from '../models/task.model';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiUrl = 'http://localhost:8080/task'; // URL del backend

  constructor(private http: HttpClient,
    private authService: AuthService
  ) { }

  getTaskbyID(id: number): Observable<Task> {
    const headers = this.getAuthHeaders();
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Task>(url,{headers});
  }

  getToken() {
    return this.authService.token;
  }

  // Configurar los encabezados de autorización
  getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  
  getTaskbyIdUser(id: number): Observable<any> {
    const headers = this.getAuthHeaders();
    const url = `${this.apiUrl}/user/${id}`;
    return this.http.get<any>(url,{headers});
  }

  // Método para crear una tarea
  createTask(
    taskPayload: { 
      column: number; 
      title: string; 
      descripcion: string; 
      priority: number; 
      limitDate: string; 
      user: number 
    }): Observable<any> {
    const headers = this.getAuthHeaders();
    const url = `${this.apiUrl}/save`;
    return this.http.post<any>(url, taskPayload, { headers });
  }  

  // Método para actualizar una tarea
  updateTask(
    id: number,
    taskPayload: {
      column: number | undefined;
      title: string | undefined;
      descripcion: string | undefined;
      priority: number | undefined;
      limitDate: string | undefined;
      user: number | undefined;
    }
  ): Observable<{ success: boolean; message: string }> {
    const headers = this.getAuthHeaders();
    const url = `${this.apiUrl}/update/${id}`; // URL interpolada correctamente

    return this.http.put<{ success: boolean; message: string }>(url, taskPayload, { headers }).pipe(
      catchError((error) => {
        console.error('Error al actualizar la tarea:', error);
        return throwError(() => new Error('No se pudo actualizar la tarea. Intente nuevamente.'));
      })
    );
  }

}
