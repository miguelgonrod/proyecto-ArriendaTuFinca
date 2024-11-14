import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';  // Ensure correct import
import { CalificacionesComponent } from './calificaciones/calificaciones.component';
import { PagosComponent } from './pagos/pagos.component';
import { LoginComponent } from './login/login.component';
import { PropiedadesComponent } from './propiedades/propiedades.component';
import { SolicitudesComponent } from './solicitudes/solicitudes.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
  { path: 'calificaciones', component: CalificacionesComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'pagos', component: PagosComponent },
  { path: 'propiedades', component: PropiedadesComponent },
  { path: 'solicitudes', component: SolicitudesComponent },
  { path: 'usuarios', component: UsuariosComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
