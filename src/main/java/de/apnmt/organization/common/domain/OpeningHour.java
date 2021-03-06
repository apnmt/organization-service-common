package de.apnmt.organization.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.apnmt.common.enumeration.Day;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * A OpeningHour.
 */
@Entity
@Table(name = "opening_hour")
public class OpeningHour implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "opening_hour_sequence", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Day day;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JsonIgnoreProperties(value = {"closingTimes", "openingHours", "employees", "addresse"}, allowSetters = true)
    private Organization organization;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OpeningHour id(Long id) {
        this.id = id;
        return this;
    }

    public Day getDay() {
        return this.day;
    }

    public OpeningHour day(Day day) {
        this.day = day;
        return this;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public OpeningHour startTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public OpeningHour endTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public OpeningHour organization(Organization organization) {
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
        if (!(o instanceof OpeningHour)) {
            return false;
        }
        return this.id != null && this.id.equals(((OpeningHour) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpeningHour{" +
                "id=" + getId() +
                ", day='" + getDay() + "'" +
                ", startTime='" + getStartTime() + "'" +
                ", endTime='" + getEndTime() + "'" +
                "}";
    }
}
