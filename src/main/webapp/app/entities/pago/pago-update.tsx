import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getReservas } from 'app/entities/reserva/reserva.reducer';
import { MetodoPago } from 'app/shared/model/enumerations/metodo-pago.model';
import { createEntity, getEntity, reset, updateEntity } from './pago.reducer';

export const PagoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const reservas = useAppSelector(state => state.reserva.entities);
  const pagoEntity = useAppSelector(state => state.pago.entity);
  const loading = useAppSelector(state => state.pago.loading);
  const updating = useAppSelector(state => state.pago.updating);
  const updateSuccess = useAppSelector(state => state.pago.updateSuccess);
  const metodoPagoValues = Object.keys(MetodoPago);

  const handleClose = () => {
    navigate(`/pago${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getReservas({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    values.fechaPago = convertDateTimeToServer(values.fechaPago);
    if (values.monto !== undefined && typeof values.monto !== 'number') {
      values.monto = Number(values.monto);
    }

    const entity = {
      ...pagoEntity,
      ...values,
      reserva: reservas.find(it => it.id.toString() === values.reserva?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          fechaPago: displayDefaultDateTime(),
        }
      : {
          metodo: 'TARJETA_CREDITO',
          ...pagoEntity,
          fechaPago: convertDateTimeFromServer(pagoEntity.fechaPago),
          reserva: pagoEntity?.reserva?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="aerolineaVirtualApp.pago.home.createOrEditLabel" data-cy="PagoCreateUpdateHeading">
            <Translate contentKey="aerolineaVirtualApp.pago.home.createOrEditLabel">Create or edit a Pago</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="pago-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('aerolineaVirtualApp.pago.fechaPago')}
                id="pago-fechaPago"
                name="fechaPago"
                data-cy="fechaPago"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('aerolineaVirtualApp.pago.monto')}
                id="pago-monto"
                name="monto"
                data-cy="monto"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('aerolineaVirtualApp.pago.metodo')}
                id="pago-metodo"
                name="metodo"
                data-cy="metodo"
                type="select"
              >
                {metodoPagoValues.map(metodoPago => (
                  <option value={metodoPago} key={metodoPago}>
                    {translate(`aerolineaVirtualApp.MetodoPago.${metodoPago}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                id="pago-reserva"
                name="reserva"
                data-cy="reserva"
                label={translate('aerolineaVirtualApp.pago.reserva')}
                type="select"
              >
                <option value="" key="0" />
                {reservas
                  ? reservas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/pago" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PagoUpdate;
