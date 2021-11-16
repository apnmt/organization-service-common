package de.apnmt.organization.common.service.mapper;

import de.apnmt.organization.common.domain.Addresse;
import de.apnmt.organization.service.dto.AddresseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Addresse} and its DTO {@link AddresseDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganizationMapper.class })
public interface AddresseMapper extends EntityMapper<AddresseDTO, Addresse> {
    @Mapping(target = "organization", source = "organization", qualifiedByName = "id")
    AddresseDTO toDto(Addresse s);
}
