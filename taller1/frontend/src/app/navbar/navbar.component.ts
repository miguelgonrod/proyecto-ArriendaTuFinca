import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';  // Importa RouterModule

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule],  // Importa RouterModule
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {}
