import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    // Verifica si el token existe en localStorage
    const token = localStorage.getItem('token');
    if (token) {
      return true; // Usuario autenticado, permitir acceso
    } else {
      this.router.navigate(['/login']); // Redirigir a login si no está autenticado
      return false;
    }
  }
}
