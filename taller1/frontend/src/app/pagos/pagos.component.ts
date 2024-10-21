import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';  // Para directivas comunes como *ngFor
import { HttpClientModule } from '@angular/common/http';  // Para usar HttpClient
import { PagoService } from '../services/pagos.service'; // Servicio de pagos
import { PagoDTO } from '../models/pago.model';  // Modelo del DTO

@Component({
  selector: 'app-pagos',
  standalone: true,  // Componente standalone
  imports: [CommonModule, HttpClientModule],  // Importamos HttpClientModule aquí
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.scss'],
  providers: [PagoService]  // Asegúrate de que el servicio esté en los providers
})
export class PagosComponent implements OnInit {
  pagos: PagoDTO[] = [];  // Lista de pagos

  constructor(private pagoService: PagoService) {}

  ngOnInit(): void {
    this.obtenerPagos();
  }

  obtenerPagos(): void {
    this.pagoService.getPagos().subscribe(
      (data: PagoDTO[]) => {
        this.pagos = data;  // Asignamos los datos de los pagos
      },
      (error) => {
        console.error('Error al obtener pagos:', error);
      }
    );
  }
}