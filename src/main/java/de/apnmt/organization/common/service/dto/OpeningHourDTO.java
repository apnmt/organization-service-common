package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.OpeningHour;
import de.apnmt.organization.domain.enumeration.Day;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link OpeningHour} entity.
 */
public class OpeningHourDTO implements Serializable {

    private Long id;

    private Day day;

    @NotNull
    private Instant startTime;

    @NotNull
    private Instant endTime;

    private OrganizationDTO organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public OrganizationDTO getOrganization() {
        return organization;
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
