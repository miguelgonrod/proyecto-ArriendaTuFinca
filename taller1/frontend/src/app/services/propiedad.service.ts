import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { PropiedadDTO } from '../models/propiedad.model';  // Modelo del DTO

@Injectable({
  providedIn: 'root',
})
export class PropiedadService {
  private apiUrl = 'http://localhost:8080/api/propiedades';  // URL del backend (puedes cambiarla)

  constructor(private http: HttpClient) {}

  // Obtener todas las propiedades
  getPropiedades(): Observable<PropiedadDTO[]> {
    // Datos dummy para pruebas si el backend no está disponible
    const dummyPropiedades: PropiedadDTO[] = [
      { id: 1, direccion: 'Calle 123', descripcion: 'Bonita casa', precio: 250000, municipio: 'Bogotá', numeroPersonas: 4, estado: 'Disponible', usuarioId: 1 },
      { id: 2, direccion: 'Carrera 45', descripcion: 'Departamento en el centro', precio: 150000, municipio: 'Medellín', numeroPersonas: 2, estado: 'Alquilada', usuarioId: 2 },
      { id: 3, direccion: 'Avenida 68', descripcion: 'Casa grande con jardín', precio: 350000, municipio: 'Cali', numeroPersonas: 5, estado: 'Disponible', usuarioId: 3 },
    ];

    // Aquí hacemos la llamada al backend, pero usamos datos dummy si falla
    return this.http.get<PropiedadDTO[]>(this.apiUrl).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error al obtener propiedades del backend, usando datos dummy:', error);
        return of(dummyPropiedades);  // Devolvemos los datos dummy en caso de error
      })
    );
  }
}
