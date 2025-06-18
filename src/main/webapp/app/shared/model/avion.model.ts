export interface IAvion {
  id?: number;
  matricula?: string;
  modelo?: string;
  capacidad?: number;
}

export const defaultValue: Readonly<IAvion> = {};
