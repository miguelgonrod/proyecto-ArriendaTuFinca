import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';  // Si usas un standalone component
import { HttpClientModule } from '@angular/common/http';
import { CalificacionService } from '../services/calificaciones.service'; // Importamos el servicio
import { CalificacionDTO } from '../models/calificacion.model';  // Importamos el modelo

@Component({
  selector: 'app-calificaciones',
  standalone: true,  // Si lo usas como standalone
  imports: [CommonModule, HttpClientModule],
  templateUrl: './calificaciones.component.html',
  styleUrls: ['./calificaciones.component.scss'],
  providers: [CalificacionService]  // Asegúrate de que el servicio esté en los providers
})
export class CalificacionesComponent implements OnInit {
  calificaciones: CalificacionDTO[] = [];  // Lista para almacenar las calificaciones

  constructor(private calificacionService: CalificacionService) {}

  ngOnInit(): void {
    this.obtenerCalificaciones();  // Al inicializar el componente, llamamos a esta función
  }

  // Método para obtener las calificaciones desde el servicio
  obtenerCalificaciones(): void {
    this.calificacionService.getCalificaciones().subscribe(
      (data: CalificacionDTO[]) => {
        this.calificaciones = data;  // Asignamos los datos de las calificaciones
      },
      (error) => {
        console.error('Error al obtener calificaciones:', error);
        // Aquí podrías manejar el error si es necesario
      }
    );
  }
}