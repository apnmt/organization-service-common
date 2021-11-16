package de.apnmt.organization.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * A ClosingTime.
 */
@Entity
@Table(name = "closing_time")
public class ClosingTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Instant startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Instant endAt;

    @ManyToOne
    @JsonIgnoreProperties(value = { "closingTimes", "openingHours", "employees", "addresse" }, allowSetters = true)
    private Organization organization;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClosingTime id(Long id) {
        this.id = id;
        return this;
    }

    public Instant getStartAt() {
        return this.startAt;
    }

    public ClosingTime startAt(Instant startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return this.endAt;
    }

    public ClosingTime endAt(Instant endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public ClosingTime organization(Organization organization) {
        this.setOrganization(organization);
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClosingTime)) {
            return false;
        }
        return id != null && id.equals(((ClosingTime) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClosingTime{" +
            "id=" + getId() +
            ", startAt='" + getStartAt() + "'" +
            ", endAt='" + getEndAt() + "'" +
            "}";
    }
}
