import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-solicitudes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './solicitudes.component.html',
  styleUrls: ['./solicitudes.component.scss']
})
export class SolicitudesComponent {
  solicitudes = [
    { 
      propiedad: 'Casa en el campo', 
      solicitante: 'Juan Pérez', 
      cedula: '12345678', 
      telefono: '987654321', 
      mensaje: 'Estoy interesado en rentar.', 
      fechaReserva: new Date(2024, 0, 10), // Fecha de solicitud de reserva
      fechaIngreso: new Date(2024, 0, 15), // Fecha de ingreso
      fechaSalida: new Date(2024, 0, 20)   // Fecha de salida
    },
    // Puedes agregar más datos de ejemplo aquí
  ];

  aprobarSolicitud(solicitud: any) {
    console.log("Solicitud aprobada:", solicitud);
    // Lógica para aprobar solicitud
  }

  rechazarSolicitud(solicitud: any) {
    console.log("Solicitud rechazada:", solicitud);
    // Lógica para rechazar solicitud
  }
}

