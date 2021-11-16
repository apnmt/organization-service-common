package de.apnmt.organization.common.service;

import de.apnmt.organization.domain.ClosingTime;
import de.apnmt.organization.repository.ClosingTimeRepository;
import de.apnmt.organization.service.dto.ClosingTimeDTO;
import de.apnmt.organization.service.mapper.ClosingTimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClosingTime}.
 */
@Service
@Transactional
public class ClosingTimeService {

    private final Logger log = LoggerFactory.getLogger(ClosingTimeService.class);

    private final ClosingTimeRepository closingTimeRepository;

    private final ClosingTimeMapper closingTimeMapper;

    public ClosingTimeService(ClosingTimeRepository closingTimeRepository, ClosingTimeMapper closingTimeMapper) {
        this.closingTimeRepository = closingTimeRepository;
        this.closingTimeMapper = closingTimeMapper;
    }

    /**
     * Save a closingTime.
     *
     * @param closingTimeDTO the entity to save.
     * @return the persisted entity.
     */
    public ClosingTimeDTO save(ClosingTimeDTO closingTimeDTO) {
        log.debug("Request to save ClosingTime : {}", closingTimeDTO);
        ClosingTime closingTime = closingTimeMapper.toEntity(closingTimeDTO);
        closingTime = closingTimeRepository.save(closingTime);
        return closingTimeMapper.toDto(closingTime);
    }

    /**
     * Partially update a closingTime.
     *
     * @param closingTimeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ClosingTimeDTO> partialUpdate(ClosingTimeDTO closingTimeDTO) {
        log.debug("Request to partially update ClosingTime : {}", closingTimeDTO);

        return closingTimeRepository
            .findById(closingTimeDTO.getId())
            .map(
                existingClosingTime -> {
                    closingTimeMapper.partialUpdate(existingClosingTime, closingTimeDTO);

                    return existingClosingTime;
                }
            )
            .map(closingTimeRepository::save)
            .map(closingTimeMapper::toDto);
    }

    /**
     * Get all the closingTimes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ClosingTimeDTO> findAll() {
        log.debug("Request to get all ClosingTimes");
        return closingTimeRepository.findAll().stream().map(closingTimeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one closingTime by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ClosingTimeDTO> findOne(Long id) {
        log.debug("Request to get ClosingTime : {}", id);
        return closingTimeRepository.findById(id).map(closingTimeMapper::toDto);
    }

    /**
     * Delete the closingTime by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ClosingTime : {}", id);
        closingTimeRepository.deleteById(id);
    }
}
