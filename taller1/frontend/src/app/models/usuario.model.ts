export class UsuarioDTO {
  id: number;
  nombre: string;
  email: string;
  password: string;
  telefono: string;
  role: string; // Campo adicional

  constructor(
    id: number,
    nombre: string,
    email: string,
    password: string,
    telefono: string,
    role: string // Campo adicional en el constructor
  ) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.password = password;
    this.telefono = telefono;
    this.role = role; // Asignación del campo
  }
}
