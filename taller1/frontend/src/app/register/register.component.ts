import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';



@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [AuthService]
})
export class RegisterComponent {
  username: string = '';
  email: string = '';
  password: string = '';
  phone: string = '';
  role: 'arrendador' | 'arrendatario' = 'arrendador';

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    // Llamar al servicio de registro
    this.authService.register(this.username, this.email, this.password, this.phone, this.role).subscribe(
      (token) => {
        console.log('Usuario registrado y token recibido:', token);
        // Guardar el token en localStorage
        localStorage.setItem('authToken', token);
        // Redirigir al usuario a la página de login
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Error en el registro:', error);
      }
    );
  }
}
