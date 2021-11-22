package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.ClosingTime;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link ClosingTime} entity.
 */
public class ClosingTimeDTO implements Serializable {

    private static final long serialVersionUID = 6306496168077255894L;
    private Long id;

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;

    private OrganizationDTO organization;

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
