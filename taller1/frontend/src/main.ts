import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import { routes } from './app/app.routes'; // Importa tus rutas

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),  // Usa provideRouter para la configuración de rutas
  ],
}).catch((err) => console.error(err));
