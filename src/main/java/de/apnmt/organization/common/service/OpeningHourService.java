package de.apnmt.organization.common.service;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.ApnmtEventType;
import de.apnmt.common.event.value.OpeningHourEventDTO;
import de.apnmt.common.sender.ApnmtEventSender;
import de.apnmt.organization.common.domain.OpeningHour;
import de.apnmt.organization.common.repository.OpeningHourRepository;
import de.apnmt.organization.common.service.dto.OpeningHourDTO;
import de.apnmt.organization.common.service.mapper.OpeningHourEventMapper;
import de.apnmt.organization.common.service.mapper.OpeningHourMapper;
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
 * Service Implementation for managing {@link OpeningHour}.
 */
@Service
@Transactional
public class OpeningHourService {

    private final Logger log = LoggerFactory.getLogger(OpeningHourService.class);

    private final OpeningHourRepository openingHourRepository;

    private final OpeningHourMapper openingHourMapper;

    private final ApnmtEventSender<OpeningHourEventDTO> sender;

    private final OpeningHourEventMapper openingHourEventMapper;

    public OpeningHourService(OpeningHourRepository openingHourRepository, OpeningHourMapper openingHourMapper, ApnmtEventSender<OpeningHourEventDTO> sender, OpeningHourEventMapper openingHourEventMapper) {
        this.openingHourRepository = openingHourRepository;
        this.openingHourMapper = openingHourMapper;
        this.sender = sender;
        this.openingHourEventMapper = openingHourEventMapper;
    }

    /**
     * Save a openingHour.
     *
     * @param openingHourDTO the entity to save.
     * @return the persisted entity.
     */
    public OpeningHourDTO save(OpeningHourDTO openingHourDTO) {
        this.log.debug("Request to save OpeningHour : {}", openingHourDTO);
        OpeningHour openingHour = this.openingHourMapper.toEntity(openingHourDTO);
        openingHour = this.openingHourRepository.save(openingHour);
        this.sender.send(TopicConstants.OPENING_HOUR_CHANGED_TOPIC, createEvent(openingHour, ApnmtEventType.openingHourCreated));
        return this.openingHourMapper.toDto(openingHour);
    }

    /**
     * Partially update a openingHour.
     *
     * @param openingHourDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OpeningHourDTO> partialUpdate(OpeningHourDTO openingHourDTO) {
        this.log.debug("Request to partially update OpeningHour : {}", openingHourDTO);

        return this.openingHourRepository
                .findById(openingHourDTO.getId())
                .map(
                        existingOpeningHour -> {
                            this.openingHourMapper.partialUpdate(existingOpeningHour, openingHourDTO);

                            return existingOpeningHour;
                        }
                )
                .map(this.openingHourRepository::save)
                .map(openingHour -> {
                    this.sender.send(TopicConstants.OPENING_HOUR_CHANGED_TOPIC, createEvent(openingHour, ApnmtEventType.openingHourCreated));
                    return openingHour;
                })
                .map(this.openingHourMapper::toDto);
    }

    /**
     * Get all the openingHours.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OpeningHourDTO> findAll() {
        this.log.debug("Request to get all OpeningHours");
        return this.openingHourRepository.findAll().stream().map(this.openingHourMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one openingHour by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OpeningHourDTO> findOne(Long id) {
        this.log.debug("Request to get OpeningHour : {}", id);
        return this.openingHourRepository.findById(id).map(this.openingHourMapper::toDto);
    }

    /**
     * Delete the openingHour by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        this.log.debug("Request to delete OpeningHour : {}", id);
        Optional<OpeningHour> maybe = this.openingHourRepository.findById(id);
        ApnmtEvent<OpeningHourEventDTO> event;
        if (maybe.isPresent()) {
            event = createEvent(maybe.get(), ApnmtEventType.openingHourDeleted);
        } else {
            event = createEvent(new OpeningHour().id(id), ApnmtEventType.openingHourDeleted);
        }
        this.sender.send(TopicConstants.OPENING_HOUR_CHANGED_TOPIC, event);
        this.openingHourRepository.deleteById(id);
    }

    private ApnmtEvent<OpeningHourEventDTO> createEvent(OpeningHour openingHour, ApnmtEventType type) {
        return new ApnmtEvent<OpeningHourEventDTO>().timestamp(LocalDateTime.now()).type(type).value(this.openingHourEventMapper.toDto(openingHour));
    }
}
