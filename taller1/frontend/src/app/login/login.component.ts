import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';  // Import the AuthService
import { FormsModule } from '@angular/forms';  // Importa FormsModule

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [FormsModule],
  styleUrls: ['./login.component.css'],
  providers: [AuthService]  // Make sure the AuthService is in the providers
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  role: 'arrendador' | 'arrendatario' = 'arrendador';

  constructor(private router: Router, private authService: AuthService) {}

  login() {
    console.log('Login method called');
  
    this.authService.login(this.email, this.password).subscribe({
      next: (response: any) => {
        // Guarda el token JWT en localStorage
        this.authService.saveToken(response.token);
  
        // Redirige según el rol
        if (this.role === 'arrendador') {
          this.router.navigate(['/arrendador']);
        } else {
          this.router.navigate(['/arrendatario']);
        }
      },
      error: (err) => {
        console.error('Error al iniciar sesión:', err);
        alert('Error al iniciar sesión. Por favor verifica tus credenciales.');
      }
    });
  }

  goToSignUp() {
    this.router.navigate(['/register']);
  }
}
