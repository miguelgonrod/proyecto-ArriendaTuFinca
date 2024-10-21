import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  // Importamos HttpClient
import { Observable, of } from 'rxjs';  // Usamos 'of' para emitir datos simulados
import { catchError } from 'rxjs/operators';  // Importamos catchError para manejar errores
import { PagoDTO } from '../models/pago.model';  // Modelo de PagoDTO

@Injectable({
  providedIn: 'root'  // Servicio disponible globalmente
})
export class PagoService {
  private apiUrl = 'http://localhost:8080/api/pagos';  // URL del backend (dummy aquí)

  constructor(private http: HttpClient) {}  // Inyectamos HttpClient

  // Método que usa dummy data si no tienes backend aún
  getPagos(): Observable<PagoDTO[]> {
    const dummyPagos: PagoDTO[] = [
      new PagoDTO(1, 150.0, new Date('2023-10-05'), 1, 1),
      new PagoDTO(2, 200.0, new Date('2023-10-06'), 2, 2),
    ];

    return this.http.get<PagoDTO[]>(this.apiUrl).pipe(
      catchError(error => {
        console.error('Error al obtener pagos del backend, usando datos dummy:', error);
        return of(dummyPagos);  // Emitimos datos dummy en caso de error
      })
    );
  }
}