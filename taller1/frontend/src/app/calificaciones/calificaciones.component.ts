import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';  // Si usas un standalone component
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';  // Importamos FormsModule
import { CalificacionService } from '../services/calificaciones.service'; // Importamos el servicio
import { CalificacionDTO } from '../models/calificacion.model';  // Importamos el modelo

@Component({
  selector: 'app-calificaciones',
  standalone: true,  // Si lo usas como standalone
  imports: [CommonModule, HttpClientModule, FormsModule],  // Añadimos FormsModule aquí
  templateUrl: './calificaciones.component.html',
  styleUrls: ['./calificaciones.component.scss'],
  providers: [CalificacionService]  // Asegúrate de que el servicio esté en los providers
})
export class CalificacionesComponent implements OnInit {
  calificaciones: CalificacionDTO[] = [];  // Lista para almacenar las calificaciones
  nuevaCalificacion: CalificacionDTO = { id: 0, puntuacion: 0, comentario: '', usuarioId: 1, propiedadId: 1 };  // Nueva calificación para el formulario

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

  // Método para crear una nueva calificación
  crearCalificacion(): void {
    this.calificacionService.createCalificacion(this.nuevaCalificacion).subscribe(
      (data: CalificacionDTO) => {
        this.calificaciones.push(data);  // Añadimos la nueva calificación a la lista
        this.nuevaCalificacion = { id: 0, puntuacion: 0, comentario: '', usuarioId: 1, propiedadId: 1 };  // Reseteamos el formulario
      },
      (error) => {
        console.error('Error al crear calificación:', error);
      }
    );
  }

  // Método para eliminar una calificación
  eliminarCalificacion(id: number): void {
    this.calificacionService.deleteCalificacion(id).subscribe(
      () => {
        this.calificaciones = this.calificaciones.filter(calificacion => calificacion.id !== id);  // Eliminamos la calificación de la lista
      },
      (error) => {
        console.error('Error al eliminar calificación:', error);
      }
    );
  }
}