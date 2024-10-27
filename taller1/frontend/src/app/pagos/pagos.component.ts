import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';  // Importamos FormsModule
import { PagoService } from '../services/pagos.service';  // Servicio de pagos
import { PagoDTO } from '../models/pago.model';  // Modelo del DTO

@Component({
  selector: 'app-pagos',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],  // Añadimos FormsModule aquí
  templateUrl: './pagos.component.html',
  styleUrls: ['./pagos.component.scss'],
  providers: [PagoService]  // Asegúrate de que el servicio esté en los providers
})
export class PagosComponent implements OnInit {
  pagos: PagoDTO[] = [];  // Lista de pagos
  nuevoPago: PagoDTO = new PagoDTO(0, 0, new Date(), 1, 1);  // Nuevo pago para el formulario

  constructor(private pagoService: PagoService) {}

  ngOnInit(): void {
    this.obtenerPagos();  // Al inicializar el componente, llamamos a esta función
  }

  // Método para obtener los pagos desde el servicio
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

  // Método para crear un nuevo pago
  crearPago(): void {
    this.nuevoPago.fechaPago = new Date(this.nuevoPago.fechaPago);
    this.pagoService.createPago(this.nuevoPago).subscribe(
      (data: PagoDTO) => {
        this.pagos.push(data);  // Añadimos el nuevo pago a la lista
        this.nuevoPago = new PagoDTO(0, 0, new Date(), 1, 1);  // Reseteamos el formulario
      },
      (error) => {
        console.error('Error al crear pago:', error);
      }
    );
  }

  // Método para eliminar un pago
  eliminarPago(id: number): void {
    this.pagoService.deletePago(id).subscribe(
      () => {
        this.pagos = this.pagos.filter(pago => pago.id !== id);  // Eliminamos el pago de la lista
      },
      (error) => {
        console.error('Error al eliminar pago:', error);
      }
    );
  }

  // Método para formatear la fecha a 'yyyy-MM-dd'
  private formatDate(date: Date | string): string {
    const d = new Date(date);
    const month = '' + (d.getMonth() + 1);
    const day = '' + d.getDate();
    const year = d.getFullYear();

    return [year, month.padStart(2, '0'), day.padStart(2, '0')].join('-');
  }
}