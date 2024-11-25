import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PropiedadService } from '../../services/propiedad.service';
import { AuthService } from '../../services/auth.service';
import { PropiedadDTO } from '../../models/propiedad.model';

@Component({
  selector: 'app-mis-propiedades',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './mis-propiedades.component.html',
  styleUrls: ['./mis-propiedades.component.scss']
})
export class MisPropiedadesComponent implements OnInit {
  propiedades: PropiedadDTO[] = [];
  propiedadEnEdicion: PropiedadDTO | null = null;

  constructor(
    private propiedadService: PropiedadService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.cargarPropiedades();
  }

  cargarPropiedades() {
    const userId = this.authService.getCurrentUserId();
    if (userId !== null) {
      this.propiedadService.getPropiedadesByUsuarioId(userId).subscribe(
        (propiedades: PropiedadDTO[]) => {
          this.propiedades = propiedades;
          console.log('Propiedades cargadas:', this.propiedades);
        },
        (error: any) => {
          console.error('Error al cargar propiedades:', error);
        }
      );
    } else {
      console.error('Error: userId is null');
    }
  }

  editarPropiedad(propiedad: PropiedadDTO) {
    this.propiedadEnEdicion = { ...propiedad };
    this.mostrarModal();
  }

  guardarEdicion() {
    if (this.propiedadEnEdicion) {
      this.propiedadService.updatePropiedad(this.propiedadEnEdicion.id, this.propiedadEnEdicion).subscribe(
        (propiedadActualizada: PropiedadDTO) => {
          const index = this.propiedades.findIndex(p => p.id === propiedadActualizada.id);
          if (index !== -1) {
            this.propiedades[index] = propiedadActualizada;
            console.log('Propiedad actualizada:', propiedadActualizada);
          }
          this.cancelarEdicion();
        },
        (error: any) => {
          console.error('Error al actualizar propiedad:', error);
        }
      );
    }
  }

  cancelarEdicion() {
    this.propiedadEnEdicion = null;
    this.ocultarModal();
  }

  eliminarPropiedad(propiedad: PropiedadDTO) {
    this.propiedadService.deletePropiedad(propiedad.id).subscribe(
      () => {
        this.propiedades = this.propiedades.filter(p => p !== propiedad);
        console.log('Propiedad eliminada:', propiedad);
      },
      (error: any) => {
        console.error('Error al eliminar propiedad:', error);
      }
    );
  }

  mostrarModal() {
    const modal = document.getElementById('editarModal');
    if (modal) {
      modal.style.display = 'block';
    }
  }

  ocultarModal() {
    const modal = document.getElementById('editarModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }
}