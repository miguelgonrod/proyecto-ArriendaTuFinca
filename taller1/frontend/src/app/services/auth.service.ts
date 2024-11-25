import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { jwtDecode } from "jwt-decode";
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.authenticationApiUrl;

  constructor(private http: HttpClient) {}

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { email, password });
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getRoleFromToken(token: string): string {
    try {
      const decoded: any = jwtDecode(token); // Decodifica el token
      console.log('Token decodificado role:', decoded.userId);
      return decoded.role; // Retorna el rol del usuario
    } catch (error) {
      console.error('Error al decodificar el token:', error);
      return '';
    }
  }

  getCurrentUserId(): number | null {
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('No hay token disponible');
      return null;
    }
    try {
      const decoded: any = jwtDecode(token); // Decodifica el token
      console.log('Token decodificado id:', decoded.userId);
      

      // Asegúrate de que el campo coincide con tu token (puede ser 'id', 'userId' o 'sub')
      const userId = decoded.userId || decoded.sub || decoded.id;
      console.log('User ID extraído del token:', userId);

      return userId ? Number(userId) : null;
    } catch (error) {
      console.error('Error al decodificar el token:', error);
      return null;
    }
  }
  
}
