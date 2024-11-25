import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SolicitudService } from '../../services/solicitud.service';
import { SolicitudDTO } from '../../models/solicitud.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-pago',
  standalone: true,
  imports: [],
  templateUrl: './pago.component.html',
  styleUrls: ['./pago.component.css']
})
export class PagoComponent implements OnInit {
  monto: number | undefined;
  descripcion: string | undefined;
  propiedadId: number | undefined;
  usuarioId: number | undefined;

  constructor(
    private route: ActivatedRoute,
    private solicitudService: SolicitudService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params: { [x: string]: any; }) => {
      this.monto = params['monto'];
      this.descripcion = `Pago de propiedad: ${params['nombre']} - ${params['descripcion']}`;
      this.propiedadId = params['propiedadId'];
    });

    // Obtener el ID del usuario actual
    const userId = this.authService.getCurrentUserId();
    if (userId === null) {
      console.error('Error: userId is null');
      this.usuarioId = undefined;
    } else {
      this.usuarioId = userId;
    }
  }

  pagar() {
    const fechaSolicitud = new Date().toISOString().split('T')[0]; // Fecha actual en formato YYYY-MM-DD
    const fechaEntrada = prompt('Ingrese la fecha de inicio (YYYY-MM-DD):');
    const fechaSalida = prompt('Ingrese la fecha de fin (YYYY-MM-DD):');

    if (fechaEntrada && fechaSalida) {
      const solicitud: SolicitudDTO = {
        id: 0, // ID autogenerado
        estado: 'Abierta',
        fechaSolicitud: fechaSolicitud,
        fechaEntrada: fechaEntrada,
        fechaSalida: fechaSalida,
        usuarioId: this.usuarioId!,
        propiedadId: this.propiedadId!
      };

      this.solicitudService.createSolicitud(solicitud).subscribe(
        (response: any) => {
          alert('Pago exitoso y solicitud creada.');
          this.router.navigate(['/inmuebles']);
        },
        (error: any) => {
          console.error('Error al crear la solicitud:', error);
          alert('Error al crear la solicitud.');
        }
      );
    } else {
      alert('Debe ingresar ambas fechas.');
    }
  }
}