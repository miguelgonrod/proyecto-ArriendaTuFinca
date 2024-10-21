export class SolicitudDTO {
    id: number;
    estado: string;
    fechaSolicitud: string;  // Fecha como string (YYYY-MM-DD)
    fechaEntrada: string;
    fechaSalida: string;
    usuarioId: number;
    propiedadId: number;
  
    constructor(
      id: number,
      estado: string,
      fechaSolicitud: string,
      fechaEntrada: string,
      fechaSalida: string,
      usuarioId: number,
      propiedadId: number
    ) {
      this.id = id;
      this.estado = estado;
      this.fechaSolicitud = fechaSolicitud;
      this.fechaEntrada = fechaEntrada;
      this.fechaSalida = fechaSalida;
      this.usuarioId = usuarioId;
      this.propiedadId = propiedadId;
    }
  }
  