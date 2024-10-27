import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';  // Importamos FormsModule
import { SolicitudService } from '../services/solicitud.service';  // Servicio de solicitudes
import { SolicitudDTO } from '../models/solicitud.model'; // Modelo del DTO

@Component({
  selector: 'app-solicitudes',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],  // Añadimos FormsModule aquí
  templateUrl: './solicitudes.component.html',
  styleUrls: ['./solicitudes.component.scss'],
  providers: [SolicitudService],  // Aseguramos que el servicio esté disponible
})
export class SolicitudesComponent implements OnInit {
  solicitudes: SolicitudDTO[] = [];  // Lista para almacenar las solicitudes
  nuevaSolicitud: SolicitudDTO = new SolicitudDTO(0, 'Pendiente', this.formatDate(new Date()), this.formatDate(new Date()), this.formatDate(new Date()), 1, 1);  // Nueva solicitud para el formulario

  constructor(private solicitudService: SolicitudService) {}

  ngOnInit(): void {
    this.obtenerSolicitudes();  // Obtenemos las solicitudes al inicializar el componente
  }

  // Método para obtener las solicitudes
  obtenerSolicitudes(): void {
    this.solicitudService.getSolicitudes().subscribe(
      (data: SolicitudDTO[]) => {
        this.solicitudes = data;  // Asignamos los datos de solicitudes
      },
      (error) => {
        console.error('Error al obtener solicitudes:', error);
        // Aquí puedes manejar el error si es necesario
      }
    );
  }

  // Método para crear una nueva solicitud
  crearSolicitud(): void {
    this.nuevaSolicitud.fechaSolicitud = this.formatDate(this.nuevaSolicitud.fechaSolicitud);
    this.nuevaSolicitud.fechaEntrada = this.formatDate(this.nuevaSolicitud.fechaEntrada);
    this.nuevaSolicitud.fechaSalida = this.formatDate(this.nuevaSolicitud.fechaSalida);
    this.solicitudService.createSolicitud(this.nuevaSolicitud).subscribe(
      (data: SolicitudDTO) => {
        this.solicitudes.push(data);  // Añadimos la nueva solicitud a la lista
        this.nuevaSolicitud = new SolicitudDTO(0, 'Pendiente', this.formatDate(new Date()), this.formatDate(new Date()), this.formatDate(new Date()), 1, 1);  // Reseteamos el formulario
      },
      (error) => {
        console.error('Error al crear solicitud:', error);
      }
    );
  }

  // Método para eliminar una solicitud
  eliminarSolicitud(id: number): void {
    this.solicitudService.deleteSolicitud(id).subscribe(
      () => {
        this.solicitudes = this.solicitudes.filter(solicitud => solicitud.id !== id);  // Eliminamos la solicitud de la lista
      },
      (error) => {
        console.error('Error al eliminar solicitud:', error);
      }
    );
  }

  // Método para formatear la fecha a 'yyyy-MM-dd'
  private formatDate(date: Date | string): string {
    const d = new Date(date);
    const month = '' + (d.getMonth() + 1);
    const day = '' + d.getDate();
    const year = d.getFullYear();

    return [year, month.padStart(2, '0'), day.padStart(2, '0')].join('-');
  }
}