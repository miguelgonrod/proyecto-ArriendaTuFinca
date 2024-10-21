import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-propiedades',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './propiedades.component.html',
  styleUrl: './propiedades.component.scss'
})
export class PropiedadesComponent {
propiedades: any;

}
