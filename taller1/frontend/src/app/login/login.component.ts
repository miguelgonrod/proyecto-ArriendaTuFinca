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
    console.log("Login method called");

    // Call the authentication API
    this.authService.login(this.email, this.password).subscribe({
      next: (token: string) => {
        // Save the JWT token to localStorage or sessionStorage
        localStorage.setItem('jwtToken', token);

        // Redirect based on the role
        if (this.role === 'arrendador') {
          this.router.navigate(['/arrendador']);
        } else {
          this.router.navigate(['/arrendatario']);
        }
      },
      error: (err) => {
        console.error('Login failed:', err);
        alert('Login failed. Please check your credentials.');
      }
    });
  }

  goToSignUp() {
    this.router.navigate(['/register']);
  }
}
