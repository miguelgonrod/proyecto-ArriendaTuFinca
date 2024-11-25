import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SolicitudDTO } from '../models/solicitud.model';  // Modelo del DTO
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SolicitudService {
  private apiUrl = environment.solicitudApiUrl;  // URL del backend

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    console.log('Token obtenido en el servicio:', token);
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  // Obtener todas las solicitudes
  getSolicitudes(): Observable<SolicitudDTO[]> {
    const headers = this.getHeaders();
    return this.http.get<SolicitudDTO[]>(this.apiUrl, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener solicitudes del backend, usando datos dummy:', error);
        return of([]);  // Devolvemos una lista vacía en caso de error
      })
    );
  }

  // Crear una nueva solicitud
  createSolicitud(solicitud: SolicitudDTO): Observable<SolicitudDTO> {
    const headers = this.getHeaders();
    return this.http.post<SolicitudDTO>(this.apiUrl, solicitud, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al crear solicitud:', error);
        return of(solicitud);  // Devolvemos la solicitud en caso de error
      })
    );
  }

  // Obtener una solicitud por ID
  getSolicitudById(id: number): Observable<SolicitudDTO | null> {
    const headers = this.getHeaders();
    return this.http.get<SolicitudDTO>(`${this.apiUrl}/${id}`, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener solicitud por ID:', error);
        return of(null);  // Devolvemos null en caso de error
      })
    );
  }

  // Actualizar una solicitud
  updateSolicitud(id: number, solicitud: SolicitudDTO): Observable<SolicitudDTO> {
    const headers = this.getHeaders();
    return this.http.put<SolicitudDTO>(`${this.apiUrl}/${id}`, solicitud, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al actualizar solicitud:', error);
        return of(solicitud);  // Devolvemos la solicitud en caso de error
      })
    );
  }

  // Eliminar una solicitud
  deleteSolicitud(id: number): Observable<void | null> {
    const headers = this.getHeaders();
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al eliminar solicitud:', error);
        return of(null);  // Devolvemos null en caso de error
      })
    );
  }
}
