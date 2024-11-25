import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-arrendador',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './arrendador.component.html',
  styleUrls: ['./arrendador.component.scss']
})
export class ArrendadorComponent {
  constructor(private router: Router) {}

  goToCrearPropiedad() {
    this.router.navigate(['/crear-propiedad']);
  }

  goToSolicitudes() {
    this.router.navigate(['/solicitudes']);
  }

  goToMisPropiedades() {
    this.router.navigate(['/mis-propiedades']);
  }
}
