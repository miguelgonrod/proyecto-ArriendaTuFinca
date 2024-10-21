import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { SolicitudService } from '../services/solicitud.service';  // Servicio de solicitudes
import { SolicitudDTO } from '../models/solicitud.model'; // Modelo del DTO

@Component({
  selector: 'app-solicitudes',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './solicitudes.component.html',
  styleUrls: ['./solicitudes.component.scss'],
  providers: [SolicitudService],  // Aseguramos que el servicio esté disponible
})
export class SolicitudesComponent implements OnInit {
  solicitudes: SolicitudDTO[] = [];  // Lista para almacenar las solicitudes

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
}
