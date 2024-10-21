import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-calificaciones',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './calificaciones.component.html',
  styleUrl: './calificaciones.component.scss'
})
export class CalificacionesComponent {
calificaciones: any;

}
