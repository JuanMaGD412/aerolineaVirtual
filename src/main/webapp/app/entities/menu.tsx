import React from 'react';
import { Translate } from 'react-jhipster';
import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/pasajero">
        <Translate contentKey="global.menu.entities.pasajero" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/empleado">
        <Translate contentKey="global.menu.entities.empleado" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/aeropuerto">
        <Translate contentKey="global.menu.entities.aeropuerto" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/avion">
        <Translate contentKey="global.menu.entities.avion" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/vuelo">
        <Translate contentKey="global.menu.entities.vuelo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/asiento">
        <Translate contentKey="global.menu.entities.asiento" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/reserva">
        <Translate contentKey="global.menu.entities.reserva" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/pago">
        <Translate contentKey="global.menu.entities.pago" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
