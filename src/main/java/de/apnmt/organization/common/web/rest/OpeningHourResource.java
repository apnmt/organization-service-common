package de.apnmt.organization.common.web.rest;

import de.apnmt.common.errors.BadRequestAlertException;
import de.apnmt.organization.common.domain.OpeningHour;
import de.apnmt.organization.common.repository.OpeningHourRepository;
import de.apnmt.organization.common.service.OpeningHourService;
import de.apnmt.organization.common.service.dto.OpeningHourDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link OpeningHour}.
 */
@RestController
@RequestMapping("/api")
public class OpeningHourResource {

    private final Logger log = LoggerFactory.getLogger(OpeningHourResource.class);

    private static final String ENTITY_NAME = "organizationServiceOpeningHour";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OpeningHourService openingHourService;

    private final OpeningHourRepository openingHourRepository;

    public OpeningHourResource(OpeningHourService openingHourService, OpeningHourRepository openingHourRepository) {
        this.openingHourService = openingHourService;
        this.openingHourRepository = openingHourRepository;
    }

    /**
     * {@code POST  /opening-hours} : Create a new openingHour.
     *
     * @param openingHourDTO the openingHourDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new openingHourDTO, or with status {@code 400 (Bad Request)} if the openingHour has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/opening-hours")
    public ResponseEntity<OpeningHourDTO> createOpeningHour(@Valid @RequestBody OpeningHourDTO openingHourDTO) throws URISyntaxException {
        this.log.debug("REST request to save OpeningHour : {}", openingHourDTO);
        if (openingHourDTO.getId() != null) {
            throw new BadRequestAlertException("A new openingHour cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpeningHourDTO result = this.openingHourService.save(openingHourDTO);
        return ResponseEntity
            .created(new URI("/api/opening-hours/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(this.applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /opening-hours/:id} : Updates an existing openingHour.
     *
     * @param id             the id of the openingHourDTO to save.
     * @param openingHourDTO the openingHourDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated openingHourDTO,
     * or with status {@code 400 (Bad Request)} if the openingHourDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the openingHourDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/opening-hours/{id}")
    public ResponseEntity<OpeningHourDTO> updateOpeningHour(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OpeningHourDTO openingHourDTO
    ) throws URISyntaxException {
        this.log.debug("REST request to update OpeningHour : {}, {}", id, openingHourDTO);
        if (openingHourDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, openingHourDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!this.openingHourRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OpeningHourDTO result = this.openingHourService.save(openingHourDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(this.applicationName, true, ENTITY_NAME, openingHourDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /opening-hours/:id} : Partial updates given fields of an existing openingHour, field will ignore if it is null
     *
     * @param id             the id of the openingHourDTO to save.
     * @param openingHourDTO the openingHourDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated openingHourDTO,
     * or with status {@code 400 (Bad Request)} if the openingHourDTO is not valid,
     * or with status {@code 404 (Not Found)} if the openingHourDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the openingHourDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/opening-hours/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<OpeningHourDTO> partialUpdateOpeningHour(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OpeningHourDTO openingHourDTO
    ) throws URISyntaxException {
        this.log.debug("REST request to partial update OpeningHour partially : {}, {}", id, openingHourDTO);
        if (openingHourDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, openingHourDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!this.openingHourRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OpeningHourDTO> result = this.openingHourService.partialUpdate(openingHourDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(this.applicationName, true, ENTITY_NAME, openingHourDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /opening-hours} : get all the openingHours.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of openingHours in body.
     */
    @GetMapping("/opening-hours")
    public List<OpeningHourDTO> getAllOpeningHours() {
        this.log.debug("REST request to get all OpeningHours");
        return this.openingHourService.findAll();
    }

    /**
     * {@code GET  /opening-hours/:id} : get the "id" openingHour.
     *
     * @param id the id of the openingHourDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the openingHourDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/opening-hours/{id}")
    public ResponseEntity<OpeningHourDTO> getOpeningHour(@PathVariable Long id) {
        this.log.debug("REST request to get OpeningHour : {}", id);
        Optional<OpeningHourDTO> openingHourDTO = this.openingHourService.findOne(id);
        return ResponseUtil.wrapOrNotFound(openingHourDTO);
    }

    /**
     * {@code DELETE  /opening-hours/:id} : delete the "id" openingHour.
     *
     * @param id the id of the openingHourDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/opening-hours/{id}")
    public ResponseEntity<Void> deleteOpeningHour(@PathVariable Long id) {
        this.log.debug("REST request to delete OpeningHour : {}", id);
        this.openingHourService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(this.applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
