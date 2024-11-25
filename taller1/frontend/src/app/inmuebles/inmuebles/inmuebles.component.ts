import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PropiedadDTO } from '../../models/propiedad.model';
import { PropiedadService } from '../../services/propiedad.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-inmuebles',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './inmuebles.component.html',
  styleUrls: ['./inmuebles.component.css']
})
export class InmueblesComponent implements OnInit {
  propiedadesFiltradas: PropiedadDTO[] = [];

  constructor(private route: ActivatedRoute, private propiedadService: PropiedadService, private router: Router) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params: { [x: string]: any; }) => {
      const nombre = params['nombre'];
      const municipio = params['municipio'];
      const numeroPersonas = params['numeroPersonas'];

      if (nombre) {
        this.propiedadService.buscarPropiedades(nombre, undefined, undefined).subscribe(
          (propiedades: PropiedadDTO[]) => {
            this.propiedadesFiltradas = propiedades;
          },
          (error: any) => {
            console.error('Error al buscar propiedades por nombre:', error);
          }
        );
      } else if (municipio) {
        this.propiedadService.buscarPropiedades(undefined, municipio, undefined).subscribe(
          (propiedades: PropiedadDTO[]) => {
            this.propiedadesFiltradas = propiedades;
          },
          (error: any) => {
            console.error('Error al buscar propiedades por municipio:', error);
          }
        );
      } else if (numeroPersonas) {
        this.propiedadService.buscarPropiedades(undefined, undefined, numeroPersonas).subscribe(
          (propiedades: PropiedadDTO[]) => {
            this.propiedadesFiltradas = propiedades;
          },
          (error: any) => {
            console.error('Error al buscar propiedades por número de personas:', error);
          }
        );
      } else {
        this.propiedadService.getPropiedades().subscribe(
          (propiedades: PropiedadDTO[]) => {
            this.propiedadesFiltradas = propiedades;
          },
          (error: any) => {
            console.error('Error al obtener todas las propiedades:', error);
          }
        );
      }
    });
  }

  navigateToPago(propiedad: PropiedadDTO) {
    this.router.navigate(['/pago'], { queryParams: { nombre: propiedad.nombre, monto: propiedad.precio, descripcion: propiedad.descripcion } });
  }
}