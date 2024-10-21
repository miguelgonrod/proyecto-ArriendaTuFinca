import { Component } from '@angular/core';
import { NavbarComponent } from "./navbar/navbar.component";
import { Router, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  imports: [NavbarComponent, RouterModule, HttpClientModule]
})
export class AppComponent {
  title = 'frontend';
}
