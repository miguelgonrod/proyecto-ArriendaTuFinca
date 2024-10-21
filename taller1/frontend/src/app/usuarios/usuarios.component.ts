import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { UsuarioService } from '../services/usuario.service';  // Servicio de usuarios
import { UsuarioDTO } from '../models/usuario.model';  // Modelo del DTO

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss'],
  providers: [UsuarioService],  // Proveedor del servicio de usuarios
})
export class UsuariosComponent implements OnInit {
  usuarios: UsuarioDTO[] = [];  // Lista para almacenar los usuarios

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.obtenerUsuarios();  // Obtenemos los usuarios al inicializar el componente
  }

  // Método para obtener los usuarios
  obtenerUsuarios(): void {
    this.usuarioService.getUsuarios().subscribe(
      (data: UsuarioDTO[]) => {
        this.usuarios = data;  // Asignamos los datos de los usuarios
      },
      (error) => {
        console.error('Error al obtener usuarios:', error);
        // Aquí puedes manejar el error si es necesario
      }
    );
  }
}
