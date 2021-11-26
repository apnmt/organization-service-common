package de.apnmt.organization.common.service.mapper;

import de.apnmt.common.event.value.ClosingTimeEventDTO;
import de.apnmt.organization.common.domain.ClosingTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link ClosingTime} and its DTO {@link ClosingTimeEventDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClosingTimeEventMapper extends EntityMapper<ClosingTimeEventDTO, ClosingTime> {
    @Override
    @Mapping(target = "organizationId", source = "organization.id")
    ClosingTimeEventDTO toDto(ClosingTime s);
}
