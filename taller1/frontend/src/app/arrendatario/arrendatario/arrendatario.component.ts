import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-arrendatario',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './arrendatario.component.html',
  styleUrl: './arrendatario.component.css'
})
export class ArrendatarioComponent {
  propertyTypes = ['Casa', 'Apartamento', 'Local', 'Oficina', 'Terreno'];
  conditions = ['Nuevo', 'Usado', 'En construcción'];
  searchForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    this.searchForm = new FormGroup({
      propertyType: new FormControl(''),
      condition: new FormControl(''),
      location: new FormControl(''),
      searchType: new FormControl(''), // Añadir este control
      searchText: new FormControl(''),
      numeroPersonas: new FormControl('')
    });
  }

  navigateToInmuebles(searchType: string) {
    const searchParams = this.searchForm.value;

    if (searchType === 'text') {
      if (!searchParams.searchType) {
        alert('Debe seleccionar un criterio de búsqueda.');
        return;
      }

      if (searchParams.searchType === 'nombre') {
        this.router.navigate(['/inmuebles'], {
          queryParams: { nombre: searchParams.searchText }
        });
      } else if (searchParams.searchType === 'municipio') {
        this.router.navigate(['/inmuebles'], {
          queryParams: { municipio: searchParams.searchText }
        });
      }
    } else if (searchType === 'number') {
      this.router.navigate(['/inmuebles'], {
        queryParams: { numeroPersonas: searchParams.numeroPersonas }
      });
    }
  }
}