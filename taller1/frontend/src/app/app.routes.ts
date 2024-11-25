import { Routes } from '@angular/router';
import { LoginComponent } from './login/login/login.component';
import { RegisterComponent } from './register/register/register.component';
import { ArrendadorComponent } from './arrendador/arrendador/arrendador.component';
import { CrearPropiedadComponent } from './crear-propiedad/crear-propiedad/crear-propiedad.component';
import { SolicitudesComponent } from './solicitudes/solicitudes/solicitudes.component';
import { MisPropiedadesComponent } from './mis-propiedades/mis-propiedades/mis-propiedades.component';
import { ArrendatarioComponent } from './arrendatario/arrendatario/arrendatario.component';
import { InmueblesComponent } from './inmuebles/inmuebles/inmuebles.component';
import { PagoComponent } from './pago/pago/pago.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'arrendador', component: ArrendadorComponent },
  { path: 'crear-propiedad', component: CrearPropiedadComponent },
  { path: 'solicitudes', component: SolicitudesComponent },
  { path: 'mis-propiedades', component: MisPropiedadesComponent },
  { path: 'arrendatario', component: ArrendatarioComponent},
  { path: 'inmuebles', component: InmueblesComponent},
  { path: 'inmuebles', component: InmueblesComponent},
  { path: 'pago', component: PagoComponent },
];