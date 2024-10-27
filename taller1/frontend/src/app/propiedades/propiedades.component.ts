import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';  // Importamos FormsModule
import { PropiedadService } from '../services/propiedad.service';  // Importamos el servicio
import { PropiedadDTO } from '../models/propiedad.model';  // Importamos el modelo
@Component({
  selector: 'app-propiedades',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],  // Añadimos FormsModule aquí
  templateUrl: './propiedades.component.html',
  styleUrls: ['./propiedades.component.scss'],
  providers: [PropiedadService]  // Asegúrate de que el servicio esté en los providers
})
export class PropiedadesComponent implements OnInit {
  propiedades: PropiedadDTO[] = [];  // Lista para almacenar propiedades
  nuevaPropiedad: PropiedadDTO = new PropiedadDTO(0, '', '', 0, '', 0, '', 0);  // Nueva propiedad para el formulario

  constructor(private propiedadService: PropiedadService) {}

  ngOnInit(): void {
    this.obtenerPropiedades();  // Al inicializar el componente, llamamos a esta función
  }

  // Método para obtener las propiedades desde el servicio
  obtenerPropiedades(): void {
    this.propiedadService.getPropiedades().subscribe(
      (data: PropiedadDTO[]) => {
        this.propiedades = data;  // Asignamos los datos de las propiedades
      },
      (error) => {
        console.error('Error al obtener propiedades:', error);
      }
    );
  }

  // Método para crear una nueva propiedad
  crearPropiedad(): void {
    this.nuevaPropiedad.usuarioId = 1;  // Asignamos el ID del usuario a 1
    this.propiedadService.createPropiedad(this.nuevaPropiedad).subscribe(
      (data: PropiedadDTO) => {
        this.propiedades.push(data);  // Añadimos la nueva propiedad a la lista
        this.nuevaPropiedad = new PropiedadDTO(0, '', '', 0, '', 0, '', 0);  // Reseteamos el formulario
      },
      (error) => {
        console.error('Error al crear propiedad:', error);
      }
    );
  }

  // Método para eliminar una propiedad
  eliminarPropiedad(id: number): void {
    this.propiedadService.deletePropiedad(id).subscribe(
      () => {
        this.propiedades = this.propiedades.filter(propiedad => propiedad.id !== id);  // Eliminamos la propiedad de la lista
      },
      (error) => {
        console.error('Error al eliminar propiedad:', error);
      }
    );
  }
}