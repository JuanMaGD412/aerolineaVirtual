import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './pago.reducer';

export const PagoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const pagoEntity = useAppSelector(state => state.pago.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="pagoDetailsHeading">
          <Translate contentKey="aerolineaVirtualApp.pago.detail.title">Pago</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{pagoEntity.id}</dd>
          <dt>
            <span id="fechaPago">
              <Translate contentKey="aerolineaVirtualApp.pago.fechaPago">Fecha Pago</Translate>
            </span>
          </dt>
          <dd>{pagoEntity.fechaPago ? <TextFormat value={pagoEntity.fechaPago} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="monto">
              <Translate contentKey="aerolineaVirtualApp.pago.monto">Monto</Translate>
            </span>
          </dt>
          <dd>{pagoEntity.monto}</dd>
          <dt>
            <span id="metodo">
              <Translate contentKey="aerolineaVirtualApp.pago.metodo">Metodo</Translate>
            </span>
          </dt>
          <dd>{pagoEntity.metodo}</dd>
          <dt>
            <Translate contentKey="aerolineaVirtualApp.pago.reserva">Reserva</Translate>
          </dt>
          <dd>{pagoEntity.reserva ? pagoEntity.reserva.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/pago" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/pago/${pagoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PagoDetail;
