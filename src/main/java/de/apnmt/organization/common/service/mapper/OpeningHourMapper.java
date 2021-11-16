package de.apnmt.organization.common.service.mapper;

import de.apnmt.organization.common.domain.OpeningHour;
import de.apnmt.organization.service.dto.OpeningHourDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link OpeningHour} and its DTO {@link OpeningHourDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganizationMapper.class })
public interface OpeningHourMapper extends EntityMapper<OpeningHourDTO, OpeningHour> {
    @Mapping(target = "organization", source = "organization", qualifiedByName = "id")
    OpeningHourDTO toDto(OpeningHour s);
}
