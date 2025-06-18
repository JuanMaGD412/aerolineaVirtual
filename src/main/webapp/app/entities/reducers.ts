import pasajero from 'app/entities/pasajero/pasajero.reducer';
import empleado from 'app/entities/empleado/empleado.reducer';
import aeropuerto from 'app/entities/aeropuerto/aeropuerto.reducer';
import avion from 'app/entities/avion/avion.reducer';
import vuelo from 'app/entities/vuelo/vuelo.reducer';
import asiento from 'app/entities/asiento/asiento.reducer';
import reserva from 'app/entities/reserva/reserva.reducer';
import pago from 'app/entities/pago/pago.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  pasajero,
  empleado,
  aeropuerto,
  avion,
  vuelo,
  asiento,
  reserva,
  pago,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
