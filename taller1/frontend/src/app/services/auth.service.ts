import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = `${environment.apiUrl}/auth`;
  private tokenKey = 'jwtToken';

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const loginData = { email, password };
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, loginData, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }
  
  register(username: string, email: string, password: string, phone: string, role: 'arrendador' | 'arrendatario'): Observable<string> {
    const registerData = { username, email, password, phone, role };

    return this.http.post<string>(`${this.apiUrl}/register`, registerData, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Verificar si el usuario está autenticado (si el token existe)
  isAuthenticated(): boolean {
    const token = this.getToken();
    // Aquí puedes agregar lógica para verificar si el token es válido, como comprobar su fecha de expiración
    return token != null;
  }

  // Método para eliminar el token (cerrar sesión)
  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
}
