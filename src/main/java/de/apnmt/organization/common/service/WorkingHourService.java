package de.apnmt.organization.common.service;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.ApnmtEventType;
import de.apnmt.common.event.value.WorkingHourEventDTO;
import de.apnmt.common.sender.ApnmtEventSender;
import de.apnmt.organization.common.domain.WorkingHour;
import de.apnmt.organization.common.repository.WorkingHourRepository;
import de.apnmt.organization.common.service.dto.WorkingHourDTO;
import de.apnmt.organization.common.service.mapper.WorkingHourEventMapper;
import de.apnmt.organization.common.service.mapper.WorkingHourMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    private final ApnmtEventSender<WorkingHourEventDTO> sender;

    private final WorkingHourEventMapper workingHourEventMapper;

    public WorkingHourService(WorkingHourRepository workingHourRepository, WorkingHourMapper workingHourMapper, ApnmtEventSender<WorkingHourEventDTO> sender, WorkingHourEventMapper workingHourEventMapper) {
        this.workingHourRepository = workingHourRepository;
        this.workingHourMapper = workingHourMapper;
        this.sender = sender;
        this.workingHourEventMapper = workingHourEventMapper;
    }

    /**
     * Save a workingHour.
     *
     * @param workingHourDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkingHourDTO save(WorkingHourDTO workingHourDTO) {
        this.log.debug("Request to save WorkingHour : {}", workingHourDTO);
        WorkingHour workingHour = this.workingHourMapper.toEntity(workingHourDTO);
        workingHour = this.workingHourRepository.save(workingHour);
        this.sender.send(TopicConstants.WORKING_HOUR_CHANGED_TOPIC, createEvent(workingHour, ApnmtEventType.workingHourCreated));
        return this.workingHourMapper.toDto(workingHour);
    }

    /**
     * Partially update a workingHour.
     *
     * @param workingHourDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WorkingHourDTO> partialUpdate(WorkingHourDTO workingHourDTO) {
        this.log.debug("Request to partially update WorkingHour : {}", workingHourDTO);

        return this.workingHourRepository
                .findById(workingHourDTO.getId())
                .map(
                        existingWorkingHour -> {
                            this.workingHourMapper.partialUpdate(existingWorkingHour, workingHourDTO);

                            return existingWorkingHour;
                        }
                )
                .map(this.workingHourRepository::save)
                .map(workingHour -> {
                    this.sender.send(TopicConstants.WORKING_HOUR_CHANGED_TOPIC, createEvent(workingHour, ApnmtEventType.workingHourCreated));
                    return workingHour;
                })
                .map(this.workingHourMapper::toDto);
    }

    /**
     * Get all the workingHours.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WorkingHourDTO> findAll() {
        this.log.debug("Request to get all WorkingHours");
        return this.workingHourRepository.findAll().stream().map(this.workingHourMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one workingHour by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkingHourDTO> findOne(Long id) {
        this.log.debug("Request to get WorkingHour : {}", id);
        return this.workingHourRepository.findById(id).map(this.workingHourMapper::toDto);
    }

    /**
     * Delete the workingHour by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        this.log.debug("Request to delete WorkingHour : {}", id);
        Optional<WorkingHour> maybe = this.workingHourRepository.findById(id);
        ApnmtEvent<WorkingHourEventDTO> event;
        if (maybe.isPresent()) {
            event = createEvent(maybe.get(), ApnmtEventType.workingHourDeleted);
        } else {
            event = createEvent(new WorkingHour().id(id), ApnmtEventType.workingHourDeleted);
        }
        this.sender.send(TopicConstants.WORKING_HOUR_CHANGED_TOPIC, event);
        this.workingHourRepository.deleteById(id);
    }

    private ApnmtEvent<WorkingHourEventDTO> createEvent(WorkingHour workingHour, ApnmtEventType type) {
        return new ApnmtEvent<WorkingHourEventDTO>().timestamp(LocalDateTime.now()).type(type).value(this.workingHourEventMapper.toDto(workingHour));
    }
}
