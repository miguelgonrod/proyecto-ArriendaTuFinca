import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-pagos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagos.component.html',
  styleUrl: './pagos.component.scss'
})
export class PagosComponent {
pagos: any;

}
