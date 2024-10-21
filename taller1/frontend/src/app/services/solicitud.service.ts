import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SolicitudDTO } from '../models/solicitud.model';  // Modelo del DTO

@Injectable({
  providedIn: 'root',
})
export class SolicitudService {
  private apiUrl = 'http://localhost:8080/api/solicitudes';  // URL del backend (puedes cambiarla)

  constructor(private http: HttpClient) {}

  // Obtener todas las solicitudes
  getSolicitudes(): Observable<SolicitudDTO[]> {
    // Datos dummy para pruebas si el backend no está disponible
    const dummySolicitudes: SolicitudDTO[] = [
      { id: 1, estado: 'Pendiente', fechaSolicitud: '2024-01-10', fechaEntrada: '2024-02-01', fechaSalida: '2024-02-10', usuarioId: 1, propiedadId: 1 },
      { id: 2, estado: 'Aprobada', fechaSolicitud: '2024-01-12', fechaEntrada: '2024-03-01', fechaSalida: '2024-03-10', usuarioId: 2, propiedadId: 2 },
      { id: 3, estado: 'Rechazada', fechaSolicitud: '2024-01-15', fechaEntrada: '2024-04-01', fechaSalida: '2024-04-10', usuarioId: 3, propiedadId: 3 },
    ];

    // Aquí hacemos la llamada al backend, pero usamos datos dummy si falla
    return this.http.get<SolicitudDTO[]>(this.apiUrl).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener solicitudes del backend, usando datos dummy:', error);
        return of(dummySolicitudes);  // Devolvemos los datos dummy en caso de error
      })
    );
  }
}
