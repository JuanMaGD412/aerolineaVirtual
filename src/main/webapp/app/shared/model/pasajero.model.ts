import dayjs from 'dayjs';

export interface IPasajero {
  id?: number;
  nombre?: string;
  apellido?: string;
  email?: string;
  telefono?: string | null;
  documentoIdentidad?: string;
  nacionalidad?: string | null;
  fechaNacimiento?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IPasajero> = {};
