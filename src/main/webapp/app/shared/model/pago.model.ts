import dayjs from 'dayjs';
import { IReserva } from 'app/shared/model/reserva.model';
import { MetodoPago } from 'app/shared/model/enumerations/metodo-pago.model';

export interface IPago {
  id?: number;
  fechaPago?: dayjs.Dayjs;
  monto?: number;
  metodo?: keyof typeof MetodoPago;
  reserva?: IReserva | null;
}

export const defaultValue: Readonly<IPago> = {};
