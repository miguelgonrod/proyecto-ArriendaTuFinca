import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PropiedadDTO } from '../../models/propiedad.model';
import { PropiedadService } from '../../services/propiedad.service';
import { AuthService } from '../../services/auth.service';  // Importa el servicio de autenticación
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-crear-propiedad',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './crear-propiedad.component.html',
  styleUrls: ['./crear-propiedad.component.scss']
})
export class CrearPropiedadComponent {
  // Propiedades del formulario
  nombre: string = '';
  maxPersonas: number | null = null;
  ciudad: string = '';
  direccion: string = '';
  numContacto: string = '';
  precio: number | null = null;
  descripcion: string = '';
  usuarioId: number = 0;

  constructor(
    private propiedadService: PropiedadService,
    private authService: AuthService  // Inyecta el servicio
  ) {}

  crearPropiedad() {
    const userId = this.authService.getCurrentUserId();
    console.log('User ID obtenido:', userId);
    if (userId === null) {
      console.error('Error: userId is null');
      return;
    }

    const nuevaPropiedad: PropiedadDTO = {
      id: 0,
      nombre: this.nombre,
      direccion: this.direccion,
      descripcion: this.descripcion,
      precio: this.precio!,
      municipio: this.ciudad,
      numeroPersonas: this.maxPersonas!,
      estado: 'Disponible',
      usuarioId: userId  // Asigna el id del usuario actual
    };
    console.log('Nueva Propiedad a crear:', nuevaPropiedad);

    this.propiedadService.createPropiedad(nuevaPropiedad).subscribe(
      (respuesta: PropiedadDTO) => {
        console.log("Propiedad creada:", respuesta);
        // Lógica adicional después de crear la propiedad
      },
      (error: any) => {
        console.error('Error al crear la propiedad:', error);
      }
    );
  }
}