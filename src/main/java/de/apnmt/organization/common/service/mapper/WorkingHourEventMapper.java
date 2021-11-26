package de.apnmt.organization.common.service.mapper;

import de.apnmt.common.event.value.WorkingHourEventDTO;
import de.apnmt.organization.common.domain.WorkingHour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link WorkingHour} and its DTO {@link WorkingHourEventDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface WorkingHourEventMapper extends EntityMapper<WorkingHourEventDTO, WorkingHour> {
    @Override
    @Mapping(target = "employeeId", source = "employee.id")
    WorkingHourEventDTO toDto(WorkingHour s);
}
