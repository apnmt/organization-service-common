package de.apnmt.organization.common.service;

import de.apnmt.organization.domain.OpeningHour;
import de.apnmt.organization.repository.OpeningHourRepository;
import de.apnmt.organization.service.dto.OpeningHourDTO;
import de.apnmt.organization.service.mapper.OpeningHourMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link OpeningHour}.
 */
@Service
@Transactional
public class OpeningHourService {

    private final Logger log = LoggerFactory.getLogger(OpeningHourService.class);

    private final OpeningHourRepository openingHourRepository;

    private final OpeningHourMapper openingHourMapper;

    public OpeningHourService(OpeningHourRepository openingHourRepository, OpeningHourMapper openingHourMapper) {
        this.openingHourRepository = openingHourRepository;
        this.openingHourMapper = openingHourMapper;
    }

    /**
     * Save a openingHour.
     *
     * @param openingHourDTO the entity to save.
     * @return the persisted entity.
     */
    public OpeningHourDTO save(OpeningHourDTO openingHourDTO) {
        log.debug("Request to save OpeningHour : {}", openingHourDTO);
        OpeningHour openingHour = openingHourMapper.toEntity(openingHourDTO);
        openingHour = openingHourRepository.save(openingHour);
        return openingHourMapper.toDto(openingHour);
    }

    /**
     * Partially update a openingHour.
     *
     * @param openingHourDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OpeningHourDTO> partialUpdate(OpeningHourDTO openingHourDTO) {
        log.debug("Request to partially update OpeningHour : {}", openingHourDTO);

        return openingHourRepository
            .findById(openingHourDTO.getId())
            .map(
                existingOpeningHour -> {
                    openingHourMapper.partialUpdate(existingOpeningHour, openingHourDTO);

                    return existingOpeningHour;
                }
            )
            .map(openingHourRepository::save)
            .map(openingHourMapper::toDto);
    }

    /**
     * Get all the openingHours.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OpeningHourDTO> findAll() {
        log.debug("Request to get all OpeningHours");
        return openingHourRepository.findAll().stream().map(openingHourMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one openingHour by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OpeningHourDTO> findOne(Long id) {
        log.debug("Request to get OpeningHour : {}", id);
        return openingHourRepository.findById(id).map(openingHourMapper::toDto);
    }

    /**
     * Delete the openingHour by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OpeningHour : {}", id);
        openingHourRepository.deleteById(id);
    }
}
