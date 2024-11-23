import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from '../models/task.model';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiUrl = 'http://localhost:8080/task'; // URL del backend

  constructor(private http: HttpClient,
    private authService: AuthService
  ) { }

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

  // Método para crear una tarea
  createTask(taskPayload: { column: number; title: string; descripcion: string; priority: number; limitDate: string; user: number }): Observable<any> {
    const headers = this.getAuthHeaders();
    const url = `${this.apiUrl}/save`;
    return this.http.post<any>(url, taskPayload, { headers });
  }

}
