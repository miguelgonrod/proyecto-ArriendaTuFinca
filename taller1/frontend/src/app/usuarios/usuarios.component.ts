import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';  // Importamos FormsModule
import { UsuarioService } from '../services/usuario.service';  // Importamos el servicio
import { UsuarioDTO } from '../models/usuario.model';  // Importamos el modelo

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],  // Añadimos FormsModule aquí
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss'],
  providers: [UsuarioService]  // Asegúrate de que el servicio esté en los providers
})
export class UsuariosComponent implements OnInit {
  usuarios: UsuarioDTO[] = [];  // Lista para almacenar los usuarios
  nuevoUsuario: UsuarioDTO = new UsuarioDTO(0, '', '', '', '', 'arrendador');  // Nuevo usuario para el formulario

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.obtenerUsuarios();  // Al inicializar el componente, llamamos a esta función
  }

  // Método para obtener los usuarios desde el servicio
  obtenerUsuarios(): void {
    this.usuarioService.getUsuarios().subscribe(
      (data: UsuarioDTO[]) => {
        this.usuarios = data;  // Asignamos los datos de los usuarios
      },
      (error) => {
        console.error('Error al obtener usuarios:', error);
      }
    );
  }

  // Método para crear un nuevo usuario
  crearUsuario(): void {
    this.usuarioService.createUsuario(this.nuevoUsuario).subscribe(
      (data: UsuarioDTO) => {
        this.usuarios.push(data);  // Añadimos el nuevo usuario a la lista
        this.nuevoUsuario = new UsuarioDTO(0, '', '', '', '', 'arrendador');  // Reseteamos el formulario
      },
      (error) => {
        console.error('Error al crear usuario:', error);
      }
    );
  }

  // Método para eliminar un usuario
  eliminarUsuario(id: number): void {
    this.usuarioService.deleteUsuario(id).subscribe(
      () => {
        this.usuarios = this.usuarios.filter(usuario => usuario.id !== id);  // Eliminamos el usuario de la lista
      },
      (error) => {
        console.error('Error al eliminar usuario:', error);
      }
    );
  }
}
