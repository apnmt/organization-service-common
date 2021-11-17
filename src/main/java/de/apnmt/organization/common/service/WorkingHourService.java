package de.apnmt.organization.common.service;

import de.apnmt.organization.common.domain.WorkingHour;
import de.apnmt.organization.common.repository.WorkingHourRepository;
import de.apnmt.organization.common.service.dto.WorkingHourDTO;
import de.apnmt.organization.common.service.mapper.WorkingHourMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WorkingHour}.
 */
@Service
@Transactional
public class WorkingHourService {

    private final Logger log = LoggerFactory.getLogger(WorkingHourService.class);

    private final WorkingHourRepository workingHourRepository;

    private final WorkingHourMapper workingHourMapper;

    public WorkingHourService(WorkingHourRepository workingHourRepository, WorkingHourMapper workingHourMapper) {
        this.workingHourRepository = workingHourRepository;
        this.workingHourMapper = workingHourMapper;
    }

    /**
     * Save a workingHour.
     *
     * @param workingHourDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkingHourDTO save(WorkingHourDTO workingHourDTO) {
        log.debug("Request to save WorkingHour : {}", workingHourDTO);
        WorkingHour workingHour = workingHourMapper.toEntity(workingHourDTO);
        workingHour = workingHourRepository.save(workingHour);
        return workingHourMapper.toDto(workingHour);
    }

    /**
     * Partially update a workingHour.
     *
     * @param workingHourDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WorkingHourDTO> partialUpdate(WorkingHourDTO workingHourDTO) {
        log.debug("Request to partially update WorkingHour : {}", workingHourDTO);

        return workingHourRepository
            .findById(workingHourDTO.getId())
            .map(
                existingWorkingHour -> {
                    workingHourMapper.partialUpdate(existingWorkingHour, workingHourDTO);

                    return existingWorkingHour;
                }
            )
            .map(workingHourRepository::save)
            .map(workingHourMapper::toDto);
    }

    /**
     * Get all the workingHours.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WorkingHourDTO> findAll() {
        log.debug("Request to get all WorkingHours");
        return workingHourRepository.findAll().stream().map(workingHourMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one workingHour by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkingHourDTO> findOne(Long id) {
        log.debug("Request to get WorkingHour : {}", id);
        return workingHourRepository.findById(id).map(workingHourMapper::toDto);
    }

    /**
     * Delete the workingHour by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkingHour : {}", id);
        workingHourRepository.deleteById(id);
    }
}
