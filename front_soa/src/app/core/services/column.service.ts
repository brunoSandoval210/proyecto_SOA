import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from '../models/task.model';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Column } from '../models/column.model';

@Injectable({
  providedIn: 'root'
})
export class ColumnService {

  private apiUrl = 'http://localhost:8080/column'; // URL del backend

  constructor(private http: HttpClient,
    private authService: AuthService
  ) { }

  getColumnbyID(id: number): Observable<Column> {
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
}
