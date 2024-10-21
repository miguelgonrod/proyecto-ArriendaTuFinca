import { Routes } from '@angular/router';
import { CalificacionesComponent } from './calificaciones/calificaciones.component';
import { PagosComponent } from './pagos/pagos.component';
import { PropiedadesComponent } from './propiedades/propiedades.component';
import { SolicitudesComponent } from './solicitudes/solicitudes.component';
import { UsuariosComponent } from './usuarios/usuarios.component';

export const routes: Routes = [
  { path: 'calificaciones', component: CalificacionesComponent },
  { path: 'pagos', component: PagosComponent },
  { path: 'propiedades', component: PropiedadesComponent },
  { path: 'solicitudes', component: SolicitudesComponent },
  { path: 'usuarios', component: UsuariosComponent },
  { path: '', redirectTo: 'calificaciones', pathMatch: 'full' },
  { path: '**', redirectTo: 'calificaciones' }
];
