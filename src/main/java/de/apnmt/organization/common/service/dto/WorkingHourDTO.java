package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.WorkingHour;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link WorkingHour} entity.
 */
public class WorkingHourDTO implements Serializable {

    private static final long serialVersionUID = 4736677133559736127L;
    private Long id;

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;

    private EmployeeDTO employee;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartAt() {
        return this.startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return this.endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public EmployeeDTO getEmployee() {
        return this.employee;
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
