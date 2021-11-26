package de.apnmt.organization.common.service.mapper;

import de.apnmt.common.event.value.OpeningHourEventDTO;
import de.apnmt.organization.common.domain.OpeningHour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link OpeningHour} and its DTO {@link OpeningHourEventDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OpeningHourEventMapper extends EntityMapper<OpeningHourEventDTO, OpeningHour> {
    @Override
    @Mapping(target = "organizationId", source = "organization.id")
    OpeningHourEventDTO toDto(OpeningHour s);
}
