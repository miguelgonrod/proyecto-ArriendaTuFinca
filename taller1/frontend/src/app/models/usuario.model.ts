export class UsuarioDTO {
    id: number;
    nombre: string;
    email: string;
    password: string;
    telefono: string;
  
    constructor(
      id: number,
      nombre: string,
      email: string,
      password: string,
      telefono: string
    ) {
      this.id = id;
      this.nombre = nombre;
      this.email = email;
      this.password = password;
      this.telefono = telefono;
    }
  }
  