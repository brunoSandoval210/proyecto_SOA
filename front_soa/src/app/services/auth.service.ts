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

  constructor(
    private http: HttpClient
  ) {}

  login({ username, password }: any): Observable<any> {
    return this.http.post(this.url + "/login", { username, password });
  }

  setToken(token: string) {
    this.token = token;
  }

  isAuth(): boolean {
    return this.token !== undefined && this.token !== null;
  }

  getToken(): string | undefined {
    return this.token;
  }

  getUser(): any {
    if (this.token) {
      const payload = JSON.parse(atob(this.token.split(".")[1]));
      return payload.sub;
    }
    return null;
  }
    
}
