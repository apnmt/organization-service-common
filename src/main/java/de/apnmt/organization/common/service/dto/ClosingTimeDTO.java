package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.ClosingTime;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ClosingTime} entity.
 */
public class ClosingTimeDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant startAt;

    @NotNull
    private Instant endAt;

    private OrganizationDTO organization;

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
        if (!(o instanceof ClosingTimeDTO)) {
            return false;
        }

        ClosingTimeDTO closingTimeDTO = (ClosingTimeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, closingTimeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClosingTimeDTO{" +
            "id=" + getId() +
            ", startAt='" + getStartAt() + "'" +
            ", endAt='" + getEndAt() + "'" +
            ", organization=" + getOrganization() +
            "}";
    }
}
