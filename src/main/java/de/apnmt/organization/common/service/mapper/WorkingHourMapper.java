package de.apnmt.organization.common.service.mapper;

import de.apnmt.organization.common.domain.WorkingHour;
import de.apnmt.organization.common.service.dto.WorkingHourDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link WorkingHour} and its DTO {@link WorkingHourDTO}.
 */
@Mapper(componentModel = "spring", uses = { EmployeeMapper.class })
public interface WorkingHourMapper extends EntityMapper<WorkingHourDTO, WorkingHour> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "id")
    WorkingHourDTO toDto(WorkingHour s);
}
