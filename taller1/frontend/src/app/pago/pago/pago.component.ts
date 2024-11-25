import { Component } from '@angular/core';

@Component({
   selector: 'app-pago',
   standalone: true,
   imports: [],
   templateUrl: './pago.component.html',
   styleUrl: './pago.component.css'
})
export class PagoComponent {
   monto = 50000
    descripcion = "Pago de propiedad con nombre de la propiedad y monto de la propiedad "
}