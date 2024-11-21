import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CalificacionDTO } from '../models/calificacion.model';  // Define este modelo para que coincida con los datos del backend
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CalificacionService {
  private apiUrl = environment.calificacionesApiUrl;  // URL de tu backend

  constructor(private http: HttpClient) {}

  // Obtener todas las calificaciones
  getCalificaciones(): Observable<CalificacionDTO[]> {
    return this.http.get<CalificacionDTO[]>(this.apiUrl).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener calificaciones del backend, usando datos dummy:', error);
        return of([]);  // Devolvemos una lista vacía en caso de error
      })
    );
  }

  // Crear una nueva calificación
  createCalificacion(calificacion: CalificacionDTO): Observable<CalificacionDTO> {
    return this.http.post<CalificacionDTO>(this.apiUrl, calificacion).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al crear calificación:', error);
        return of(calificacion);  // Devolvemos la calificación en caso de error
      })
    );
  }

  // Obtener una calificación por ID
  getCalificacionById(id: number): Observable<CalificacionDTO | null> {
    return this.http.get<CalificacionDTO>(`${this.apiUrl}/${id}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener calificación por ID:', error);
        return of(null);  // Devolvemos null en caso de error
      })
    );
  }

  // Actualizar una calificación
  updateCalificacion(id: number, calificacion: CalificacionDTO): Observable<CalificacionDTO> {
    return this.http.put<CalificacionDTO>(`${this.apiUrl}/${id}`, calificacion).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al actualizar calificación:', error);
        return of(calificacion);  // Devolvemos la calificación en caso de error
      })
    );
  }

  // Eliminar una calificación
  deleteCalificacion(id: number): Observable<void | null> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al eliminar calificación:', error);
        return of(null);  // Devolvemos null en caso de error
      })
    );
  }
}
