package de.apnmt.organization.common.service.mapper;

import de.apnmt.organization.common.domain.ClosingTime;
import de.apnmt.organization.common.service.dto.ClosingTimeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link ClosingTime} and its DTO {@link ClosingTimeDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganizationMapper.class })
public interface ClosingTimeMapper extends EntityMapper<ClosingTimeDTO, ClosingTime> {
    @Mapping(target = "organization", source = "organization", qualifiedByName = "id")
    ClosingTimeDTO toDto(ClosingTime s);
}
