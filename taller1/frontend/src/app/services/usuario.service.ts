import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UsuarioDTO } from '../models/usuario.model'; // Modelo del DTO

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8080/api/usuarios';  // URL del backend (cambiar según sea necesario)

  constructor(private http: HttpClient) {}

  // Obtener todos los usuarios
  getUsuarios(): Observable<UsuarioDTO[]> {
    // Datos dummy para pruebas si el backend no está disponible
    const dummyUsuarios: UsuarioDTO[] = [
      { id: 1, nombre: 'John Doe', email: 'john.doe@example.com', password: '1234', telefono: '555-1234' },
      { id: 2, nombre: 'Jane Smith', email: 'jane.smith@example.com', password: '1234', telefono: '555-5678' },
      { id: 3, nombre: 'Alice Johnson', email: 'alice.johnson@example.com', password: '1234', telefono: '555-9876' },
    ];

    // Hacemos la llamada al backend, pero usamos datos dummy si falla
    return this.http.get<UsuarioDTO[]>(this.apiUrl).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener usuarios del backend, usando datos dummy:', error);
        return of(dummyUsuarios);  // Devolvemos los datos dummy en caso de error
      })
    );
  }
}
