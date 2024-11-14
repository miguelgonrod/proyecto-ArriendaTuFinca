import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AuthService } from './services/auth.service';
import { of } from 'rxjs';

// Mocks del servicio y del router
class AuthServiceMock {
  isAuthenticated() {
    return true;  // Simula que el usuario está autenticado
  }
}

class RouterMock {
  navigate(path: string[]) {
    // Simula la navegación (solo para pruebas)
    console.log('Navegando a:', path);
  }
}

describe('AuthGuard', () => {
  let authGuard: AuthGuard;
  let authService: AuthServiceMock;
  let router: RouterMock;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AuthGuard,
        { provide: AuthService, useClass: AuthServiceMock },  // Mock del servicio de autenticación
        { provide: Router, useClass: RouterMock }  // Mock del router
      ]
    });
    authGuard = TestBed.inject(AuthGuard);
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
  });

  it('should allow activation if user is authenticated', () => {
    spyOn(authService, 'isAuthenticated').and.returnValue(true);  // Simula que el usuario está autenticado
    const result = authGuard.canActivate({} as any, {} as any);
    expect(result).toBe(true);  // El guard debería permitir la navegación
  });

  it('should redirect to login if user is not authenticated', () => {
    spyOn(authService, 'isAuthenticated').and.returnValue(false);  // Simula que el usuario no está autenticado
    spyOn(router, 'navigate');  // Espía la función de navegación
    const result = authGuard.canActivate({} as any, {} as any);
    expect(result).toBe(false);  // El guard debería bloquear la navegación
    expect(router.navigate).toHaveBeenCalledWith(['/login']);  // Verifica que el router fue llamado con la URL de login
  });
});
