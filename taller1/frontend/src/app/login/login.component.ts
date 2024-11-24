// src/app/login/login.component.ts
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [FormsModule],
  styleUrls: ['./login.component.css'],
  providers: [AuthService]
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  role: 'arrendador' | 'arrendatario' = 'arrendador';

  constructor(private router: Router, private authService: AuthService) {}

  login() {
    console.log('Login method called');
    console.log('Email:', this.email);
    console.log('Password:', this.password);
  
    this.authService.login(this.email, this.password).subscribe({
      next: (response: any) => {
        console.log('Login successful, token received:', response.token);
        this.authService.saveToken(response.token);
  
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