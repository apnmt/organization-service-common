package de.apnmt.organization.common.service;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.ApnmtEventType;
import de.apnmt.common.event.value.ClosingTimeEventDTO;
import de.apnmt.common.sender.ApnmtEventSender;
import de.apnmt.organization.common.domain.ClosingTime;
import de.apnmt.organization.common.domain.Organization;
import de.apnmt.organization.common.repository.ClosingTimeRepository;
import de.apnmt.organization.common.repository.OrganizationRepository;
import de.apnmt.organization.common.service.dto.ClosingTimeDTO;
import de.apnmt.organization.common.service.dto.EmployeeDTO;
import de.apnmt.organization.common.service.mapper.ClosingTimeEventMapper;
import de.apnmt.organization.common.service.mapper.ClosingTimeMapper;
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
 * Service Implementation for managing {@link ClosingTime}.
 */
@Service
@Transactional
public class ClosingTimeService {

    private final Logger log = LoggerFactory.getLogger(ClosingTimeService.class);

    private final ClosingTimeRepository closingTimeRepository;

    private final OrganizationRepository organizationRepository;

    private final ClosingTimeMapper closingTimeMapper;

    private final ApnmtEventSender<ClosingTimeEventDTO> sender;

    private final ClosingTimeEventMapper closingTimeEventMapper;

    public ClosingTimeService(ClosingTimeRepository closingTimeRepository, OrganizationRepository organizationRepository, ClosingTimeMapper closingTimeMapper, ApnmtEventSender<ClosingTimeEventDTO> sender, ClosingTimeEventMapper closingTimeEventMapper) {
        this.closingTimeRepository = closingTimeRepository;
        this.organizationRepository = organizationRepository;
        this.closingTimeMapper = closingTimeMapper;
        this.sender = sender;
        this.closingTimeEventMapper = closingTimeEventMapper;
    }

    /**
     * Save a closingTime.
     *
     * @param closingTimeDTO the entity to save.
     * @return the persisted entity.
     */
    public ClosingTimeDTO save(ClosingTimeDTO closingTimeDTO) {
        this.log.debug("Request to save ClosingTime : {}", closingTimeDTO);
        ClosingTime closingTime = this.closingTimeMapper.toEntity(closingTimeDTO);
        closingTime = this.closingTimeRepository.save(closingTime);
        this.sender.send(TopicConstants.CLOSING_TIME_CHANGED_TOPIC, createEvent(closingTime, ApnmtEventType.closingTimeCreated));
        return this.closingTimeMapper.toDto(closingTime);
    }

    /**
     * Partially update a closingTime.
     *
     * @param closingTimeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ClosingTimeDTO> partialUpdate(ClosingTimeDTO closingTimeDTO) {
        this.log.debug("Request to partially update ClosingTime : {}", closingTimeDTO);

        return this.closingTimeRepository
                .findById(closingTimeDTO.getId())
                .map(
                        existingClosingTime -> {
                            this.closingTimeMapper.partialUpdate(existingClosingTime, closingTimeDTO);

                            return existingClosingTime;
                        }
                )
                .map(this.closingTimeRepository::save)
                .map(closingTime -> {
                    this.sender.send(TopicConstants.CLOSING_TIME_CHANGED_TOPIC, createEvent(closingTime, ApnmtEventType.closingTimeCreated));
                    return closingTime;
                })
                .map(this.closingTimeMapper::toDto);
    }

    /**
     * Get all the closingTimes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ClosingTimeDTO> findAll() {
        this.log.debug("Request to get all ClosingTimes");
        return this.closingTimeRepository.findAll().stream().map(this.closingTimeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the closingTimes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ClosingTimeDTO> findAllForOrganization(Long id) {
        log.debug("Request to get all ClosingTimes for Organization {}", id);
        Organization organization = organizationRepository.getOne(id);
        return closingTimeRepository.findAllByOrganization(organization).stream().map(closingTimeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one closingTime by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ClosingTimeDTO> findOne(Long id) {
        this.log.debug("Request to get ClosingTime : {}", id);
        return this.closingTimeRepository.findById(id).map(this.closingTimeMapper::toDto);
    }

    /**
     * Delete the closingTime by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        this.log.debug("Request to delete ClosingTime : {}", id);
        Optional<ClosingTime> maybe = this.closingTimeRepository.findById(id);
        ApnmtEvent<ClosingTimeEventDTO> event;
        if (maybe.isPresent()) {
            event = createEvent(maybe.get(), ApnmtEventType.closingTimeDeleted);
        } else {
            event = createEvent(new ClosingTime().id(id), ApnmtEventType.closingTimeDeleted);
        }
        this.sender.send(TopicConstants.CLOSING_TIME_CHANGED_TOPIC, event);
        this.closingTimeRepository.deleteById(id);
    }

    private ApnmtEvent<ClosingTimeEventDTO> createEvent(ClosingTime closingTime, ApnmtEventType type) {
        return new ApnmtEvent<ClosingTimeEventDTO>().timestamp(LocalDateTime.now()).type(type).value(this.closingTimeEventMapper.toDto(closingTime));
    }
}
