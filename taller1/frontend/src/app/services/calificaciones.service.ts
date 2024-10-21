import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CalificacionDTO } from '../models/calificacion.model';  // Define este modelo para que coincida con los datos del backend

@Injectable({
  providedIn: 'root',
})
export class CalificacionService {
  private apiUrl = 'http://localhost:8080/api/calificaciones';  // URL de tu backend

  constructor(private http: HttpClient) {}

  // Obtener todas las calificaciones
  getCalificaciones(): Observable<CalificacionDTO[]> {
    const dummyCalificaciones: CalificacionDTO[] = [
      { id: 1, puntuacion: 8, comentario: 'Buen servicio', usuarioId: 1, propiedadId: 1 },
      { id: 2, puntuacion: 5, comentario: 'Podría mejorar', usuarioId: 2, propiedadId: 2 },
      { id: 3, puntuacion: 10, comentario: 'Excelente', usuarioId: 3, propiedadId: 3 },
    ];

    return this.http.get<CalificacionDTO[]>(this.apiUrl).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener calificaciones del backend, usando datos dummy:', error);
        if (error.status === 200 && error.error instanceof ErrorEvent) {
          console.error('Error de parsing:', error.error.message);
        }
        return of(dummyCalificaciones);  // Emitimos datos dummy en caso de error
      })
    );
  }
}