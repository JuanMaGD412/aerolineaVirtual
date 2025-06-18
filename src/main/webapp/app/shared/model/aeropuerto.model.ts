export interface IAeropuerto {
  id?: number;
  codigo?: string;
  nombre?: string;
  ciudad?: string;
  pais?: string;
}

export const defaultValue: Readonly<IAeropuerto> = {};
