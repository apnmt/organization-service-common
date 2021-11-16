package de.apnmt.organization.common.service;

import de.apnmt.organization.domain.Addresse;
import de.apnmt.organization.repository.AddresseRepository;
import de.apnmt.organization.service.dto.AddresseDTO;
import de.apnmt.organization.service.mapper.AddresseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Addresse}.
 */
@Service
@Transactional
public class AddresseService {

    private final Logger log = LoggerFactory.getLogger(AddresseService.class);

    private final AddresseRepository addresseRepository;

    private final AddresseMapper addresseMapper;

    public AddresseService(AddresseRepository addresseRepository, AddresseMapper addresseMapper) {
        this.addresseRepository = addresseRepository;
        this.addresseMapper = addresseMapper;
    }

    /**
     * Save a addresse.
     *
     * @param addresseDTO the entity to save.
     * @return the persisted entity.
     */
    public AddresseDTO save(AddresseDTO addresseDTO) {
        log.debug("Request to save Addresse : {}", addresseDTO);
        Addresse addresse = addresseMapper.toEntity(addresseDTO);
        addresse = addresseRepository.save(addresse);
        return addresseMapper.toDto(addresse);
    }

    /**
     * Partially update a addresse.
     *
     * @param addresseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AddresseDTO> partialUpdate(AddresseDTO addresseDTO) {
        log.debug("Request to partially update Addresse : {}", addresseDTO);

        return addresseRepository
            .findById(addresseDTO.getId())
            .map(
                existingAddresse -> {
                    addresseMapper.partialUpdate(existingAddresse, addresseDTO);

                    return existingAddresse;
                }
            )
            .map(addresseRepository::save)
            .map(addresseMapper::toDto);
    }

    /**
     * Get all the addresses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AddresseDTO> findAll() {
        log.debug("Request to get all Addresses");
        return addresseRepository.findAll().stream().map(addresseMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one addresse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AddresseDTO> findOne(Long id) {
        log.debug("Request to get Addresse : {}", id);
        return addresseRepository.findById(id).map(addresseMapper::toDto);
    }

    /**
     * Delete the addresse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Addresse : {}", id);
        addresseRepository.deleteById(id);
    }
}
