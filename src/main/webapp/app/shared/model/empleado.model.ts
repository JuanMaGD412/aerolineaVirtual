export interface IEmpleado {
  id?: number;
  nombre?: string;
  apellido?: string;
  cargo?: string;
  email?: string;
  telefono?: string | null;
}

export const defaultValue: Readonly<IEmpleado> = {};
