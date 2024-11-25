import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { UsuarioDTO } from '../../models/usuario.model';
import { HttpClientModule } from '@angular/common/http';



@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule, HttpClientModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: UsuarioDTO = new UsuarioDTO(0, '', '', '', '', 'arrendador');

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    this.authService.register(this.user).subscribe(
      (response: any) => {
        console.log('Usuario registrado con éxito:', response);
        alert('Registro exitoso, puedes iniciar sesión.');
        this.router.navigate(['/login']); // Redirige al login después del registro
      },
      (error: any) => {
        console.error('Error en el registro:', error);
        alert('Hubo un error al registrar el usuario.');
      }
    );
  }
}
