export class PropiedadDTO {
    id: number;
    direccion: string;
    descripcion: string;
    precio: number;
    municipio: string;
    numeroPersonas: number;
    estado: string;
    usuarioId: number;
  
    constructor(
      id: number,
      direccion: string,
      descripcion: string,
      precio: number,
      municipio: string,
      numeroPersonas: number,
      estado: string,
      usuarioId: number
    ) {
      this.id = id;
      this.direccion = direccion;
      this.descripcion = descripcion;
      this.precio = precio;
      this.municipio = municipio;
      this.numeroPersonas = numeroPersonas;
      this.estado = estado;
      this.usuarioId = usuarioId;
    }
  }
  