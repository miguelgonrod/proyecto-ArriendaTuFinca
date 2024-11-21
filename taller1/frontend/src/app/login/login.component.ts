import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service'; // Importa el AuthService
import { FormsModule } from '@angular/forms'; // Importa FormsModule

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [FormsModule],
  styleUrls: ['./login.component.css'],
  providers: [AuthService], // Asegúrate de que AuthService esté en los providers
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    // Verificar si ya está autenticado
    const token = localStorage.getItem('token');
    if (token) {
      const userRole = this.authService.getRoleFromToken(token); // Obtener el rol del token
      this.redirectBasedOnRole(userRole); // Redirigir según el rol
    }
  }

  login() {
    this.authService.login(this.email, this.password).subscribe({
      next: (response: any) => {
        localStorage.setItem('token', response.token); // Guardar el token en localStorage
        const userRole = this.authService.getRoleFromToken(response.token); // Obtener el rol del token
        this.redirectBasedOnRole(userRole); // Redirigir según el rol
      },
      error: (err) => {
        console.error('Error en el login:', err);
        alert('Error en las credenciales');
      },
    });
  }

  redirectBasedOnRole(role: string) {
    if (role === 'arrendador') {
      this.router.navigate(['/propiedades']); // Redirige a propiedades
    } else if (role === 'arrendatario') {
      this.router.navigate(['/solicitudes']); // Redirige a solicitudes
    } else {
      console.error('Rol desconocido:', role);
      alert('No se puede determinar el rol del usuario');
    }
  }

  goToSignUp() {
    this.router.navigate(['/register']);
  }
}
