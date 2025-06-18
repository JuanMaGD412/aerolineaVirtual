import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './empleado.reducer';

export const EmpleadoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const empleadoEntity = useAppSelector(state => state.empleado.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="empleadoDetailsHeading">
          <Translate contentKey="aerolineaVirtualApp.empleado.detail.title">Empleado</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.id}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="aerolineaVirtualApp.empleado.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.nombre}</dd>
          <dt>
            <span id="apellido">
              <Translate contentKey="aerolineaVirtualApp.empleado.apellido">Apellido</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.apellido}</dd>
          <dt>
            <span id="cargo">
              <Translate contentKey="aerolineaVirtualApp.empleado.cargo">Cargo</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.cargo}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="aerolineaVirtualApp.empleado.email">Email</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.email}</dd>
          <dt>
            <span id="telefono">
              <Translate contentKey="aerolineaVirtualApp.empleado.telefono">Telefono</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.telefono}</dd>
        </dl>
        <Button tag={Link} to="/empleado" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/empleado/${empleadoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmpleadoDetail;
