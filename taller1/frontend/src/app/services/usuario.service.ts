import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UsuarioDTO } from '../models/usuario.model'; // Modelo del DTO
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private apiUrl = environment.usuariosApiUrl;

  constructor(private http: HttpClient) {}

  // Obtener todos los usuarios
  getUsuarios(): Observable<UsuarioDTO[]> {
    return this.http.get<UsuarioDTO[]>(this.apiUrl).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener usuarios del backend, usando datos dummy:', error);
        return of([]);  // Devolvemos una lista vacía en caso de error
      })
    );
  }

  // Crear un nuevo usuario
  createUsuario(usuario: UsuarioDTO): Observable<UsuarioDTO> {
    return this.http.post<UsuarioDTO>(this.apiUrl, usuario).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al crear usuario:', error);
        return of(usuario);  // Devolvemos el usuario en caso de error
      })
    );
  }

  // Obtener un usuario por ID
  getUsuarioById(id: number): Observable<UsuarioDTO | null> {
    return this.http.get<UsuarioDTO>(`${this.apiUrl}/${id}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener usuario por ID:', error);
        return of(null);  // Devolvemos null en caso de error
      })
    );
  }

  // Actualizar un usuario
  updateUsuario(id: number, usuario: UsuarioDTO): Observable<UsuarioDTO> {
    return this.http.put<UsuarioDTO>(`${this.apiUrl}/${id}`, usuario).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al actualizar usuario:', error);
        return of(usuario);  // Devolvemos el usuario en caso de error
      })
    );
  }

  // Eliminar un usuario
  deleteUsuario(id: number): Observable<void | null> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al eliminar usuario:', error);
        return of(null);  // Devolvemos null en caso de error
      })
    );
  }
}
