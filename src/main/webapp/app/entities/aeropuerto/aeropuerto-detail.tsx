import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './aeropuerto.reducer';

export const AeropuertoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const aeropuertoEntity = useAppSelector(state => state.aeropuerto.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="aeropuertoDetailsHeading">
          <Translate contentKey="aerolineaVirtualApp.aeropuerto.detail.title">Aeropuerto</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{aeropuertoEntity.id}</dd>
          <dt>
            <span id="codigo">
              <Translate contentKey="aerolineaVirtualApp.aeropuerto.codigo">Codigo</Translate>
            </span>
          </dt>
          <dd>{aeropuertoEntity.codigo}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="aerolineaVirtualApp.aeropuerto.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{aeropuertoEntity.nombre}</dd>
          <dt>
            <span id="ciudad">
              <Translate contentKey="aerolineaVirtualApp.aeropuerto.ciudad">Ciudad</Translate>
            </span>
          </dt>
          <dd>{aeropuertoEntity.ciudad}</dd>
          <dt>
            <span id="pais">
              <Translate contentKey="aerolineaVirtualApp.aeropuerto.pais">Pais</Translate>
            </span>
          </dt>
          <dd>{aeropuertoEntity.pais}</dd>
        </dl>
        <Button tag={Link} to="/aeropuerto" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/aeropuerto/${aeropuertoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AeropuertoDetail;
