import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getAeropuertos } from 'app/entities/aeropuerto/aeropuerto.reducer';
import { getEntities as getAvions } from 'app/entities/avion/avion.reducer';
import { getEntities as getEmpleados } from 'app/entities/empleado/empleado.reducer';
import { createEntity, getEntity, reset, updateEntity } from './vuelo.reducer';

export const VueloUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const aeropuertos = useAppSelector(state => state.aeropuerto.entities);
  const avions = useAppSelector(state => state.avion.entities);
  const empleados = useAppSelector(state => state.empleado.entities);
  const vueloEntity = useAppSelector(state => state.vuelo.entity);
  const loading = useAppSelector(state => state.vuelo.loading);
  const updating = useAppSelector(state => state.vuelo.updating);
  const updateSuccess = useAppSelector(state => state.vuelo.updateSuccess);

  const handleClose = () => {
    navigate(`/vuelo${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAeropuertos({}));
    dispatch(getAvions({}));
    dispatch(getEmpleados({}));
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
    values.fechaSalida = convertDateTimeToServer(values.fechaSalida);
    values.fechaLlegada = convertDateTimeToServer(values.fechaLlegada);

    const entity = {
      ...vueloEntity,
      ...values,
      aeropuertoOrigen: aeropuertos.find(it => it.id.toString() === values.aeropuertoOrigen?.toString()),
      aeropuertoDestino: aeropuertos.find(it => it.id.toString() === values.aeropuertoDestino?.toString()),
      avion: avions.find(it => it.id.toString() === values.avion?.toString()),
      piloto: empleados.find(it => it.id.toString() === values.piloto?.toString()),
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
          fechaSalida: displayDefaultDateTime(),
          fechaLlegada: displayDefaultDateTime(),
        }
      : {
          ...vueloEntity,
          fechaSalida: convertDateTimeFromServer(vueloEntity.fechaSalida),
          fechaLlegada: convertDateTimeFromServer(vueloEntity.fechaLlegada),
          aeropuertoOrigen: vueloEntity?.aeropuertoOrigen?.id,
          aeropuertoDestino: vueloEntity?.aeropuertoDestino?.id,
          avion: vueloEntity?.avion?.id,
          piloto: vueloEntity?.piloto?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="aerolineaVirtualApp.vuelo.home.createOrEditLabel" data-cy="VueloCreateUpdateHeading">
            <Translate contentKey="aerolineaVirtualApp.vuelo.home.createOrEditLabel">Create or edit a Vuelo</Translate>
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
                  id="vuelo-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('aerolineaVirtualApp.vuelo.numeroVuelo')}
                id="vuelo-numeroVuelo"
                name="numeroVuelo"
                data-cy="numeroVuelo"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('aerolineaVirtualApp.vuelo.origen')}
                id="vuelo-origen"
                name="origen"
                data-cy="origen"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('aerolineaVirtualApp.vuelo.destino')}
                id="vuelo-destino"
                name="destino"
                data-cy="destino"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('aerolineaVirtualApp.vuelo.fechaSalida')}
                id="vuelo-fechaSalida"
                name="fechaSalida"
                data-cy="fechaSalida"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('aerolineaVirtualApp.vuelo.fechaLlegada')}
                id="vuelo-fechaLlegada"
                name="fechaLlegada"
                data-cy="fechaLlegada"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="vuelo-aeropuertoOrigen"
                name="aeropuertoOrigen"
                data-cy="aeropuertoOrigen"
                label={translate('aerolineaVirtualApp.vuelo.aeropuertoOrigen')}
                type="select"
              >
                <option value="" key="0" />
                {aeropuertos
                  ? aeropuertos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="vuelo-aeropuertoDestino"
                name="aeropuertoDestino"
                data-cy="aeropuertoDestino"
                label={translate('aerolineaVirtualApp.vuelo.aeropuertoDestino')}
                type="select"
              >
                <option value="" key="0" />
                {aeropuertos
                  ? aeropuertos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="vuelo-avion"
                name="avion"
                data-cy="avion"
                label={translate('aerolineaVirtualApp.vuelo.avion')}
                type="select"
              >
                <option value="" key="0" />
                {avions
                  ? avions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="vuelo-piloto"
                name="piloto"
                data-cy="piloto"
                label={translate('aerolineaVirtualApp.vuelo.piloto')}
                type="select"
              >
                <option value="" key="0" />
                {empleados
                  ? empleados.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/vuelo" replace color="info">
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

export default VueloUpdate;
