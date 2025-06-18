package com.udea.web.rest;

import static com.udea.domain.AeropuertoAsserts.*;
import static com.udea.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.IntegrationTest;
import com.udea.domain.Aeropuerto;
import com.udea.repository.AeropuertoRepository;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AeropuertoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AeropuertoResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CIUDAD = "AAAAAAAAAA";
    private static final String UPDATED_CIUDAD = "BBBBBBBBBB";

    private static final String DEFAULT_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_PAIS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/aeropuertos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAeropuertoMockMvc;

    private Aeropuerto aeropuerto;

    private Aeropuerto insertedAeropuerto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aeropuerto createEntity() {
        return new Aeropuerto().codigo(DEFAULT_CODIGO).nombre(DEFAULT_NOMBRE).ciudad(DEFAULT_CIUDAD).pais(DEFAULT_PAIS);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aeropuerto createUpdatedEntity() {
        return new Aeropuerto().codigo(UPDATED_CODIGO).nombre(UPDATED_NOMBRE).ciudad(UPDATED_CIUDAD).pais(UPDATED_PAIS);
    }

    @BeforeEach
    void initTest() {
        aeropuerto = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedAeropuerto != null) {
            aeropuertoRepository.delete(insertedAeropuerto);
            insertedAeropuerto = null;
        }
    }

    @Test
    @Transactional
    void createAeropuerto() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Aeropuerto
        var returnedAeropuerto = om.readValue(
            restAeropuertoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aeropuerto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Aeropuerto.class
        );

        // Validate the Aeropuerto in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAeropuertoUpdatableFieldsEquals(returnedAeropuerto, getPersistedAeropuerto(returnedAeropuerto));

        insertedAeropuerto = returnedAeropuerto;
    }

    @Test
    @Transactional
    void createAeropuertoWithExistingId() throws Exception {
        // Create the Aeropuerto with an existing ID
        aeropuerto.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAeropuertoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aeropuerto)))
            .andExpect(status().isBadRequest());

        // Validate the Aeropuerto in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodigoIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        aeropuerto.setCodigo(null);

        // Create the Aeropuerto, which fails.

        restAeropuertoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aeropuerto)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNombreIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        aeropuerto.setNombre(null);

        // Create the Aeropuerto, which fails.

        restAeropuertoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aeropuerto)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCiudadIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        aeropuerto.setCiudad(null);

        // Create the Aeropuerto, which fails.

        restAeropuertoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aeropuerto)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaisIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        aeropuerto.setPais(null);

        // Create the Aeropuerto, which fails.

        restAeropuertoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aeropuerto)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAeropuertos() throws Exception {
        // Initialize the database
        insertedAeropuerto = aeropuertoRepository.saveAndFlush(aeropuerto);

        // Get all the aeropuertoList
        restAeropuertoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aeropuerto.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].ciudad").value(hasItem(DEFAULT_CIUDAD)))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)));
    }

    @Test
    @Transactional
    void getAeropuerto() throws Exception {
        // Initialize the database
        insertedAeropuerto = aeropuertoRepository.saveAndFlush(aeropuerto);

        // Get the aeropuerto
        restAeropuertoMockMvc
            .perform(get(ENTITY_API_URL_ID, aeropuerto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aeropuerto.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.ciudad").value(DEFAULT_CIUDAD))
            .andExpect(jsonPath("$.pais").value(DEFAULT_PAIS));
    }

    @Test
    @Transactional
    void getNonExistingAeropuerto() throws Exception {
        // Get the aeropuerto
        restAeropuertoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAeropuerto() throws Exception {
        // Initialize the database
        insertedAeropuerto = aeropuertoRepository.saveAndFlush(aeropuerto);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aeropuerto
        Aeropuerto updatedAeropuerto = aeropuertoRepository.findById(aeropuerto.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAeropuerto are not directly saved in db
        em.detach(updatedAeropuerto);
        updatedAeropuerto.codigo(UPDATED_CODIGO).nombre(UPDATED_NOMBRE).ciudad(UPDATED_CIUDAD).pais(UPDATED_PAIS);

        restAeropuertoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAeropuerto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAeropuerto))
            )
            .andExpect(status().isOk());

        // Validate the Aeropuerto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAeropuertoToMatchAllProperties(updatedAeropuerto);
    }

    @Test
    @Transactional
    void putNonExistingAeropuerto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aeropuerto.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAeropuertoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, aeropuerto.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aeropuerto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Aeropuerto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAeropuerto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aeropuerto.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAeropuertoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(aeropuerto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Aeropuerto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAeropuerto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aeropuerto.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAeropuertoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(aeropuerto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Aeropuerto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAeropuertoWithPatch() throws Exception {
        // Initialize the database
        insertedAeropuerto = aeropuertoRepository.saveAndFlush(aeropuerto);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aeropuerto using partial update
        Aeropuerto partialUpdatedAeropuerto = new Aeropuerto();
        partialUpdatedAeropuerto.setId(aeropuerto.getId());

        restAeropuertoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAeropuerto.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAeropuerto))
            )
            .andExpect(status().isOk());

        // Validate the Aeropuerto in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAeropuertoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAeropuerto, aeropuerto),
            getPersistedAeropuerto(aeropuerto)
        );
    }

    @Test
    @Transactional
    void fullUpdateAeropuertoWithPatch() throws Exception {
        // Initialize the database
        insertedAeropuerto = aeropuertoRepository.saveAndFlush(aeropuerto);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the aeropuerto using partial update
        Aeropuerto partialUpdatedAeropuerto = new Aeropuerto();
        partialUpdatedAeropuerto.setId(aeropuerto.getId());

        partialUpdatedAeropuerto.codigo(UPDATED_CODIGO).nombre(UPDATED_NOMBRE).ciudad(UPDATED_CIUDAD).pais(UPDATED_PAIS);

        restAeropuertoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAeropuerto.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAeropuerto))
            )
            .andExpect(status().isOk());

        // Validate the Aeropuerto in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAeropuertoUpdatableFieldsEquals(partialUpdatedAeropuerto, getPersistedAeropuerto(partialUpdatedAeropuerto));
    }

    @Test
    @Transactional
    void patchNonExistingAeropuerto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aeropuerto.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAeropuertoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, aeropuerto.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aeropuerto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Aeropuerto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAeropuerto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aeropuerto.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAeropuertoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(aeropuerto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Aeropuerto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAeropuerto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        aeropuerto.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAeropuertoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(aeropuerto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Aeropuerto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAeropuerto() throws Exception {
        // Initialize the database
        insertedAeropuerto = aeropuertoRepository.saveAndFlush(aeropuerto);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the aeropuerto
        restAeropuertoMockMvc
            .perform(delete(ENTITY_API_URL_ID, aeropuerto.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return aeropuertoRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Aeropuerto getPersistedAeropuerto(Aeropuerto aeropuerto) {
        return aeropuertoRepository.findById(aeropuerto.getId()).orElseThrow();
    }

    protected void assertPersistedAeropuertoToMatchAllProperties(Aeropuerto expectedAeropuerto) {
        assertAeropuertoAllPropertiesEquals(expectedAeropuerto, getPersistedAeropuerto(expectedAeropuerto));
    }

    protected void assertPersistedAeropuertoToMatchUpdatableProperties(Aeropuerto expectedAeropuerto) {
        assertAeropuertoAllUpdatablePropertiesEquals(expectedAeropuerto, getPersistedAeropuerto(expectedAeropuerto));
    }
}
