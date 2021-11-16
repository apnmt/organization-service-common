package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.WorkingHour;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link WorkingHour} entity.
 */
public class WorkingHourDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant startAt;

    @NotNull
    private Instant endAt;

    private EmployeeDTO employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkingHourDTO)) {
            return false;
        }

        WorkingHourDTO workingHourDTO = (WorkingHourDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workingHourDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkingHourDTO{" +
            "id=" + getId() +
            ", startAt='" + getStartAt() + "'" +
            ", endAt='" + getEndAt() + "'" +
            ", employee=" + getEmployee() +
            "}";
    }
}
