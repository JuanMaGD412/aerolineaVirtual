import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Aeropuerto from './aeropuerto';
import AeropuertoDetail from './aeropuerto-detail';
import AeropuertoUpdate from './aeropuerto-update';
import AeropuertoDeleteDialog from './aeropuerto-delete-dialog';

const AeropuertoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Aeropuerto />} />
    <Route path="new" element={<AeropuertoUpdate />} />
    <Route path=":id">
      <Route index element={<AeropuertoDetail />} />
      <Route path="edit" element={<AeropuertoUpdate />} />
      <Route path="delete" element={<AeropuertoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AeropuertoRoutes;
