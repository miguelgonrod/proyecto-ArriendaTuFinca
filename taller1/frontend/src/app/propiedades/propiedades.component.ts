import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';  // Para directivas como *ngFor
import { HttpClientModule } from '@angular/common/http';
import { PropiedadService } from '../services/propiedad.service'; // Servicio de propiedades
import { PropiedadDTO } from '../models/propiedad.model';  // Modelo del DTO

@Component({
  selector: 'app-propiedades',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './propiedades.component.html',
  styleUrls: ['./propiedades.component.scss'],
  providers: [PropiedadService],  // Aseguramos que el servicio esté disponible
})
export class PropiedadesComponent implements OnInit {
  propiedades: PropiedadDTO[] = [];  // Lista para almacenar propiedades

  constructor(private propiedadService: PropiedadService) {}

  ngOnInit(): void {
    this.obtenerPropiedades();  // Obtenemos las propiedades al inicializar el componente
  }

  // Método para obtener propiedades
  obtenerPropiedades(): void {
    this.propiedadService.getPropiedades().subscribe(
      (data: PropiedadDTO[]) => {
        this.propiedades = data;  // Asignamos los datos de propiedades
      },
      (error) => {
        console.error('Error al obtener propiedades:', error);
        // Aquí puedes manejar el error si es necesario
      }
    );
  }
}
