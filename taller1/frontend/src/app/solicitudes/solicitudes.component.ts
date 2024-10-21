import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-solicitudes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './solicitudes.component.html',
  styleUrl: './solicitudes.component.scss'
})
export class SolicitudesComponent {
solicitudes: any;

}
