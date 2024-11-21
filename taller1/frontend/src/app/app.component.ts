import { Component } from '@angular/core';
import { NavbarComponent } from "./navbar/navbar.component";
import { Router, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  imports: [NavbarComponent, RouterModule, HttpClientModule, FormsModule]
})
export class AppComponent {
  title = 'frontend';
}
