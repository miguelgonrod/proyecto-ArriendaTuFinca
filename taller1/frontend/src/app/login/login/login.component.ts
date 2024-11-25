import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    // Verificar si ya está autenticado
    if (typeof window !== 'undefined') {
      const token = localStorage.getItem('token');
      if (token) {
        const userRole = this.authService.getRoleFromToken(token); // Obtener el rol del token
        this.redirectBasedOnRole(userRole); // Redirigir según el rol
      }
    }
  }

  login() {
    this.authService.login(this.email, this.password).subscribe({
      next: (response: any) => {
        localStorage.setItem('token', response.token); // Guardar el token en localStorage
        const userRole = this.authService.getRoleFromToken(response.token); // Obtener el rol del token
        this.redirectBasedOnRole(userRole); // Redirigir según el rol
      },
      error: (err: any) => {
        console.error('Error en el login:', err);
        alert('Error en las credenciales');
      },
    });
  }

  redirectBasedOnRole(role: string) {
    if (role === 'arrendador') {
      this.router.navigate(['/arrendador']); // Redirige a propiedades
    } else if (role === 'arrendatario') {
      this.router.navigate(['/arrendatario']); // Redirige a solicitudes
    } else {
      console.error('Rol desconocido:', role);
      alert('No se puede determinar el rol del usuario');
    }
  }

  goToSignUp() {
    this.router.navigate(['/register']);
  }
}