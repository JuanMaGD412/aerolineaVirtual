import { IVuelo } from 'app/shared/model/vuelo.model';
import { ClaseAsiento } from 'app/shared/model/enumerations/clase-asiento.model';

export interface IAsiento {
  id?: number;
  numero?: string;
  clase?: keyof typeof ClaseAsiento;
  disponible?: boolean;
  vuelo?: IVuelo | null;
}

export const defaultValue: Readonly<IAsiento> = {
  disponible: false,
};
