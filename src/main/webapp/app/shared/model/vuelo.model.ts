import dayjs from 'dayjs';
import { IAeropuerto } from 'app/shared/model/aeropuerto.model';
import { IAvion } from 'app/shared/model/avion.model';
import { IEmpleado } from 'app/shared/model/empleado.model';

export interface IVuelo {
  id?: number;
  numeroVuelo?: string;
  origen?: string;
  destino?: string;
  fechaSalida?: dayjs.Dayjs;
  fechaLlegada?: dayjs.Dayjs;
  aeropuertoOrigen?: IAeropuerto | null;
  aeropuertoDestino?: IAeropuerto | null;
  avion?: IAvion | null;
  piloto?: IEmpleado | null;
}

export const defaultValue: Readonly<IVuelo> = {};
