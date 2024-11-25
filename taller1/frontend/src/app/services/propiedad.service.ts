import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { PropiedadDTO } from '../models/propiedad.model';  // Modelo del DTO
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PropiedadService {
  private apiUrl = environment.propiedadesApiUrl; // URL del backend

  constructor(private http: HttpClient) {}

  // Método para obtener los encabezados con el token JWT
  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    console.log('Token obtenido en el servicio:', token);
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  // Obtener todas las propiedades
  getPropiedades(): Observable<PropiedadDTO[]> {
    const headers = this.getHeaders();
    return this.http.get<PropiedadDTO[]>(this.apiUrl, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener propiedades del backend:', error);
        return of([]); // Devuelve una lista vacía en caso de error
      })
    );
  }

  // Crear una nueva propiedad
  createPropiedad(propiedad: PropiedadDTO): Observable<PropiedadDTO> {
    const headers = this.getHeaders();
    return this.http.post<PropiedadDTO>(this.apiUrl, propiedad, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al crear propiedad:', error);
        return of(propiedad); // Devuelve la propiedad en caso de error
      })
    );
  }

  // Obtener una propiedad por ID
  getPropiedadById(id: number): Observable<PropiedadDTO | null> {
    const headers = this.getHeaders();
    return this.http.get<PropiedadDTO>(`${this.apiUrl}/${id}`, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener propiedad por ID:', error);
        return of(null); // Devuelve null en caso de error
      })
    );
  }

  

  // Actualizar una propiedad
  updatePropiedad(id: number, propiedad: PropiedadDTO): Observable<PropiedadDTO> {
    const headers = this.getHeaders();
    return this.http.put<PropiedadDTO>(`${this.apiUrl}/${id}`, propiedad, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al actualizar propiedad:', error);
        return of(propiedad); // Devuelve la propiedad en caso de error
      })
    );
  }

   // Obtener propiedades por ID de usuario
  
  getPropiedadesByUsuarioId(usuarioId: number): Observable<PropiedadDTO[]> {
    const headers = this.getHeaders();
    return this.http.get<PropiedadDTO[]>(`${this.apiUrl}/usuario/${usuarioId}`, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener propiedades por ID de usuario:', error);
        return of([]); // Devuelve una lista vacía en caso de error
      })
    );
  }

  // propiedad.service.ts
  buscarPropiedades(nombre?: string, municipio?: string, numeroPersonas?: number): Observable<PropiedadDTO[]> {
    const params: any = {};

    if (nombre) params.nombre = nombre;
    if (municipio) params.municipio = municipio;
    if (numeroPersonas) params.numeroPersonas = numeroPersonas;

    const options = {
      params: params,
      headers: this.getHeaders()
    };

    return this.http.get<PropiedadDTO[]>(`${this.apiUrl}/buscar`, options).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al buscar propiedades:', error);
        return of([]);
      })
    );
  }

  // Eliminar una propiedad
  deletePropiedad(id: number): Observable<void | null> {
    const headers = this.getHeaders();
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers }).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al eliminar propiedad:', error);
        return of(null); // Devuelve null en caso de error
      })
    );
  }
}