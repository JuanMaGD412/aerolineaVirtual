import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, TextFormat, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './vuelo.reducer';

export const Vuelo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const vueloList = useAppSelector(state => state.vuelo.entities);
  const loading = useAppSelector(state => state.vuelo.loading);
  const totalItems = useAppSelector(state => state.vuelo.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="vuelo-heading" data-cy="VueloHeading">
        <Translate contentKey="aerolineaVirtualApp.vuelo.home.title">Vuelos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="aerolineaVirtualApp.vuelo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/vuelo/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="aerolineaVirtualApp.vuelo.home.createLabel">Create new Vuelo</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {vueloList && vueloList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('numeroVuelo')}>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.numeroVuelo">Numero Vuelo</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('numeroVuelo')} />
                </th>
                <th className="hand" onClick={sort('origen')}>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.origen">Origen</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('origen')} />
                </th>
                <th className="hand" onClick={sort('destino')}>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.destino">Destino</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('destino')} />
                </th>
                <th className="hand" onClick={sort('fechaSalida')}>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.fechaSalida">Fecha Salida</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fechaSalida')} />
                </th>
                <th className="hand" onClick={sort('fechaLlegada')}>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.fechaLlegada">Fecha Llegada</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fechaLlegada')} />
                </th>
                <th>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.aeropuertoOrigen">Aeropuerto Origen</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.aeropuertoDestino">Aeropuerto Destino</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.avion">Avion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="aerolineaVirtualApp.vuelo.piloto">Piloto</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {vueloList.map((vuelo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/vuelo/${vuelo.id}`} color="link" size="sm">
                      {vuelo.id}
                    </Button>
                  </td>
                  <td>{vuelo.numeroVuelo}</td>
                  <td>{vuelo.origen}</td>
                  <td>{vuelo.destino}</td>
                  <td>{vuelo.fechaSalida ? <TextFormat type="date" value={vuelo.fechaSalida} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{vuelo.fechaLlegada ? <TextFormat type="date" value={vuelo.fechaLlegada} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>
                    {vuelo.aeropuertoOrigen ? <Link to={`/aeropuerto/${vuelo.aeropuertoOrigen.id}`}>{vuelo.aeropuertoOrigen.id}</Link> : ''}
                  </td>
                  <td>
                    {vuelo.aeropuertoDestino ? (
                      <Link to={`/aeropuerto/${vuelo.aeropuertoDestino.id}`}>{vuelo.aeropuertoDestino.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{vuelo.avion ? <Link to={`/avion/${vuelo.avion.id}`}>{vuelo.avion.id}</Link> : ''}</td>
                  <td>{vuelo.piloto ? <Link to={`/empleado/${vuelo.piloto.id}`}>{vuelo.piloto.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/vuelo/${vuelo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/vuelo/${vuelo.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/vuelo/${vuelo.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="aerolineaVirtualApp.vuelo.home.notFound">No Vuelos found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={vueloList && vueloList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Vuelo;
