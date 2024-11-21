import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';  // Importamos HttpClient
import { Observable, of } from 'rxjs';  // Usamos 'of' para emitir datos simulados
import { catchError } from 'rxjs/operators';  // Importamos catchError para manejar errores
import { PagoDTO } from '../models/pago.model';  // Modelo de PagoDTO
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'  // Servicio disponible globalmente
})
export class PagoService {
  private apiUrl = `${environment.apiUrl}/pagos`;  // URL del backend

  constructor(private http: HttpClient) {}  // Inyectamos HttpClient

  // Obtener todos los pagos
  getPagos(): Observable<PagoDTO[]> {
    return this.http.get<PagoDTO[]>(this.apiUrl).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener pagos del backend, usando datos dummy:', error);
        return of([]);  // Devolvemos una lista vacía en caso de error
      })
    );
  }

  // Crear un nuevo pago
  createPago(pago: PagoDTO): Observable<PagoDTO> {
    return this.http.post<PagoDTO>(this.apiUrl, pago).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al crear pago:', error);
        return of(pago);  // Devolvemos el pago en caso de error
      })
    );
  }

  // Obtener un pago por ID
  getPagoById(id: number): Observable<PagoDTO | null> {
    return this.http.get<PagoDTO>(`${this.apiUrl}/${id}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener pago por ID:', error);
        return of(null);  // Devolvemos null en caso de error
      })
    );
  }

  // Actualizar un pago
  updatePago(id: number, pago: PagoDTO): Observable<PagoDTO> {
    return this.http.put<PagoDTO>(`${this.apiUrl}/${id}`, pago).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al actualizar pago:', error);
        return of(pago);  // Devolvemos el pago en caso de error
      })
    );
  }

  // Eliminar un pago
  deletePago(id: number): Observable<void | null> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al eliminar pago:', error);
        return of(null);  // Devolvemos null en caso de error
      })
    );
  }
}