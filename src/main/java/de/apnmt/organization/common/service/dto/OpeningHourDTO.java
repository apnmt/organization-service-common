package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.OpeningHour;
import de.apnmt.organization.common.domain.enumeration.Day;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 * A DTO for the {@link OpeningHour} entity.
 */
public class OpeningHourDTO implements Serializable {

    private static final long serialVersionUID = -1504543712682631148L;
    private Long id;

    private Day day;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    private OrganizationDTO organization;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Day getDay() {
        return this.day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public OrganizationDTO getOrganization() {
        return this.organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpeningHourDTO)) {
            return false;
        }

        OpeningHourDTO openingHourDTO = (OpeningHourDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, openingHourDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpeningHourDTO{" +
                "id=" + getId() +
                ", day='" + getDay() + "'" +
                ", startTime='" + getStartTime() + "'" +
                ", endTime='" + getEndTime() + "'" +
                ", organization=" + getOrganization() +
                "}";
    }
}
