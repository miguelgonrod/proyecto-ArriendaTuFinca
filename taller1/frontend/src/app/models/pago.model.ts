export class PagoDTO {
    id: number;
    monto: number;
    fechaPago: Date;
    usuarioId: number;
    propiedadId: number;
  
    constructor(id: number, monto: number, fechaPago: Date, usuarioId: number, propiedadId: number) {
      this.id = id;
      this.monto = monto;
      this.fechaPago = fechaPago;
      this.usuarioId = usuarioId;
      this.propiedadId = propiedadId;
    }
  }
  