import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TableKanbanService {

  private url: string = `${environment.apiUrl}/table`;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  // Obtener el token del usuario autenticado
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

  // Obtener tablas por usuario
  getTablesByUser(userId: number): Observable<any[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<any[]>(`${this.url}/user/${userId}`, { headers });
  }

  // Obtener tablas por usuario
  getColumnsByTable(tableId: number): Observable<any[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<any[]>(`${this.url}/columns/${tableId}`, { headers });
  }


}
