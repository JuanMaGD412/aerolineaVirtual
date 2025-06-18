import dayjs from 'dayjs';
import { IVuelo } from 'app/shared/model/vuelo.model';
import { IAsiento } from 'app/shared/model/asiento.model';
import { IPasajero } from 'app/shared/model/pasajero.model';
import { EstadoReserva } from 'app/shared/model/enumerations/estado-reserva.model';

export interface IReserva {
  id?: number;
  codigo?: string;
  fechaReserva?: dayjs.Dayjs;
  estado?: keyof typeof EstadoReserva;
  vuelo?: IVuelo | null;
  asiento?: IAsiento | null;
  pasajero?: IPasajero | null;
}

export const defaultValue: Readonly<IReserva> = {};
