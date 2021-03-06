package de.apnmt.organization.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A WorkingHour.
 */
@Entity
@Table(name = "working_hour")
public class WorkingHour implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "working_hour_sequence", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @ManyToOne
    @JsonIgnoreProperties(value = {"workingHours", "organization"}, allowSetters = true)
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkingHour id(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getStartAt() {
        return this.startAt;
    }

    public WorkingHour startAt(LocalDateTime startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return this.endAt;
    }

    public WorkingHour endAt(LocalDateTime endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public WorkingHour employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkingHour)) {
            return false;
        }
        return this.id != null && this.id.equals(((WorkingHour) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkingHour{" +
                "id=" + getId() +
                ", startAt='" + getStartAt() + "'" +
                ", endAt='" + getEndAt() + "'" +
                "}";
    }
}
