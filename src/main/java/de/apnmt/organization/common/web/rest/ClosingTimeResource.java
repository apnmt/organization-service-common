package de.apnmt.organization.common.web.rest;

import de.apnmt.organization.common.domain.ClosingTime;
import de.apnmt.organization.common.repository.ClosingTimeRepository;
import de.apnmt.organization.common.service.ClosingTimeService;
import de.apnmt.organization.common.service.dto.ClosingTimeDTO;
import de.apnmt.organization.common.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link ClosingTime}.
 */
@RestController
@RequestMapping("/api")
public class ClosingTimeResource {

    private final Logger log = LoggerFactory.getLogger(ClosingTimeResource.class);

    private static final String ENTITY_NAME = "organizationServiceClosingTime";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClosingTimeService closingTimeService;

    private final ClosingTimeRepository closingTimeRepository;

    public ClosingTimeResource(ClosingTimeService closingTimeService, ClosingTimeRepository closingTimeRepository) {
        this.closingTimeService = closingTimeService;
        this.closingTimeRepository = closingTimeRepository;
    }

    /**
     * {@code POST  /closing-times} : Create a new closingTime.
     *
     * @param closingTimeDTO the closingTimeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new closingTimeDTO, or with status {@code 400 (Bad Request)} if the closingTime has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/closing-times")
    public ResponseEntity<ClosingTimeDTO> createClosingTime(@Valid @RequestBody ClosingTimeDTO closingTimeDTO) throws URISyntaxException {
        log.debug("REST request to save ClosingTime : {}", closingTimeDTO);
        if (closingTimeDTO.getId() != null) {
            throw new BadRequestAlertException("A new closingTime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClosingTimeDTO result = closingTimeService.save(closingTimeDTO);
        return ResponseEntity
            .created(new URI("/api/closing-times/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /closing-times/:id} : Updates an existing closingTime.
     *
     * @param id the id of the closingTimeDTO to save.
     * @param closingTimeDTO the closingTimeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated closingTimeDTO,
     * or with status {@code 400 (Bad Request)} if the closingTimeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the closingTimeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/closing-times/{id}")
    public ResponseEntity<ClosingTimeDTO> updateClosingTime(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ClosingTimeDTO closingTimeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClosingTime : {}, {}", id, closingTimeDTO);
        if (closingTimeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, closingTimeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!closingTimeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClosingTimeDTO result = closingTimeService.save(closingTimeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, closingTimeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /closing-times/:id} : Partial updates given fields of an existing closingTime, field will ignore if it is null
     *
     * @param id the id of the closingTimeDTO to save.
     * @param closingTimeDTO the closingTimeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated closingTimeDTO,
     * or with status {@code 400 (Bad Request)} if the closingTimeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the closingTimeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the closingTimeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/closing-times/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ClosingTimeDTO> partialUpdateClosingTime(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ClosingTimeDTO closingTimeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClosingTime partially : {}, {}", id, closingTimeDTO);
        if (closingTimeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, closingTimeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!closingTimeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClosingTimeDTO> result = closingTimeService.partialUpdate(closingTimeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, closingTimeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /closing-times} : get all the closingTimes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of closingTimes in body.
     */
    @GetMapping("/closing-times")
    public List<ClosingTimeDTO> getAllClosingTimes() {
        log.debug("REST request to get all ClosingTimes");
        return closingTimeService.findAll();
    }

    /**
     * {@code GET  /closing-times/:id} : get the "id" closingTime.
     *
     * @param id the id of the closingTimeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the closingTimeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/closing-times/{id}")
    public ResponseEntity<ClosingTimeDTO> getClosingTime(@PathVariable Long id) {
        log.debug("REST request to get ClosingTime : {}", id);
        Optional<ClosingTimeDTO> closingTimeDTO = closingTimeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(closingTimeDTO);
    }

    /**
     * {@code DELETE  /closing-times/:id} : delete the "id" closingTime.
     *
     * @param id the id of the closingTimeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/closing-times/{id}")
    public ResponseEntity<Void> deleteClosingTime(@PathVariable Long id) {
        log.debug("REST request to delete ClosingTime : {}", id);
        closingTimeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
