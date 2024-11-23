import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../../authentication/auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  private url: string = `http://localhost:8080`;

  constructor(
    private http: HttpClient,
    private authService: AuthService) { }

    getBoards(idUser: number) {
      return this.http.get<any>(`${this.url}/table/user/${idUser}`, { headers: this.getAuthHeaders() });
    }
    
    createBoard(board: any) {
      return this.http.post<any>(this.url, board, { headers: this.getAuthHeaders() });
    }
  
    updateBoard(id: number, board: any) {
      return this.http.put<any>(`${this.url}/${id}`, board, { headers: this.getAuthHeaders() });
    }
  
    deleteBoard(id: number): Observable<any> {
      return this.http.delete<any>(`${this.url}/${id}`);
    }

    getUserId() {
      return this.authService.getUser;
    }
  
    getToken() {
      return this.authService.getToken;
    }

    getAuthHeaders(): HttpHeaders {
      const token = this.getToken();
      return new HttpHeaders({
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      });
    }
}