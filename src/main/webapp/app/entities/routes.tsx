import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Pasajero from './pasajero';
import Empleado from './empleado';
import Aeropuerto from './aeropuerto';
import Avion from './avion';
import Vuelo from './vuelo';
import Asiento from './asiento';
import Reserva from './reserva';
import Pago from './pago';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="pasajero/*" element={<Pasajero />} />
        <Route path="empleado/*" element={<Empleado />} />
        <Route path="aeropuerto/*" element={<Aeropuerto />} />
        <Route path="avion/*" element={<Avion />} />
        <Route path="vuelo/*" element={<Vuelo />} />
        <Route path="asiento/*" element={<Asiento />} />
        <Route path="reserva/*" element={<Reserva />} />
        <Route path="pago/*" element={<Pago />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
