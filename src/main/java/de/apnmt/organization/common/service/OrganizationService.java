package de.apnmt.organization.common.service;

import de.apnmt.common.event.value.OrganizationActivationEventDTO;
import de.apnmt.organization.common.domain.Organization;
import de.apnmt.organization.common.repository.OrganizationRepository;
import de.apnmt.organization.common.service.dto.OrganizationDTO;
import de.apnmt.organization.common.service.mapper.OrganizationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Organization}.
 */
@Service
@Transactional
public class OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationService.class);

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    public OrganizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    /**
     * Save a organization.
     *
     * @param organizationDTO the entity to save.
     * @return the persisted entity.
     */
    public OrganizationDTO save(OrganizationDTO organizationDTO) {
        this.log.debug("Request to save Organization : {}", organizationDTO);
        Organization organization = this.organizationMapper.toEntity(organizationDTO);
        // an organization is deactivated by default, will be activated when payment succeeded
        if (organization.getId() == null) {
            organization.setActive(false);
        }
        organization = this.organizationRepository.save(organization);
        return this.organizationMapper.toDto(organization);
    }

    /**
     * Activate a organization.
     *
     * @param organizationActivationDTO
     */
    public void handleOrganizationActivation(OrganizationActivationEventDTO organizationActivationDTO) {
        Optional<Organization> maybe = this.organizationRepository.findById(organizationActivationDTO.getOrganizationId());
        if (maybe.isPresent()) {
            Organization organization = maybe.get();
            organization.setActive(organizationActivationDTO.isActive());
            this.organizationRepository.save(organization);
        }
    }

    /**
     * Partially update a organization.
     *
     * @param organizationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OrganizationDTO> partialUpdate(OrganizationDTO organizationDTO) {
        this.log.debug("Request to partially update Organization : {}", organizationDTO);

        return this.organizationRepository.findById(organizationDTO.getId()).map(existingOrganization -> {
            this.organizationMapper.partialUpdate(existingOrganization, organizationDTO);

            return existingOrganization;
        }).map(this.organizationRepository::save).map(this.organizationMapper::toDto);
    }

    /**
     * Get all the organizations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrganizationDTO> findAll(Pageable pageable) {
        this.log.debug("Request to get all Organizations");
        return this.organizationRepository.findAllByActive(true, pageable).map(this.organizationMapper::toDto);
    }

    /**
     * Get all the organizations where Addresse is {@code null}.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrganizationDTO> findAllWhereAddresseIsNull() {
        this.log.debug("Request to get all organizations where Addresse is null");
        return StreamSupport.stream(this.organizationRepository.findAll().spliterator(), false)
                .filter(organization -> organization.getAddresse() == null)
                .map(this.organizationMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one organization by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrganizationDTO> findOne(Long id) {
        this.log.debug("Request to get Organization : {}", id);
        return this.organizationRepository.findById(id).map(this.organizationMapper::toDto);
    }

    /**
     * Delete the organization by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        this.log.debug("Request to delete Organization : {}", id);
        this.organizationRepository.deleteById(id);
    }

    /**
     * Delete all organizations.
     */
    public void deleteAll() {
        log.debug("Request to delete all organizations");
        organizationRepository.deleteAllByIdGreaterThan(251L);
    }
}
