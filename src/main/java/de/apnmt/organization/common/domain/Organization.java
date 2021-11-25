package de.apnmt.organization.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "mail", nullable = false)
    private String mail;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "organization")
    @JsonIgnoreProperties(value = {"organization"}, allowSetters = true)
    private Set<ClosingTime> closingTimes = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    @JsonIgnoreProperties(value = {"organization"}, allowSetters = true)
    private Set<OpeningHour> openingHours = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    @JsonIgnoreProperties(value = {"workingHours", "organization"}, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();

    @OneToOne(mappedBy = "organization", cascade = CascadeType.ALL)
    private Addresse addresse;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Organization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return this.mail;
    }

    public Organization mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return this.phone;
    }

    public Organization phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public Organization ownerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Organization active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<ClosingTime> getClosingTimes() {
        return this.closingTimes;
    }

    public Organization closingTimes(Set<ClosingTime> closingTimes) {
        this.setClosingTimes(closingTimes);
        return this;
    }

    public Organization addClosingTime(ClosingTime closingTime) {
        this.closingTimes.add(closingTime);
        closingTime.setOrganization(this);
        return this;
    }

    public Organization removeClosingTime(ClosingTime closingTime) {
        this.closingTimes.remove(closingTime);
        closingTime.setOrganization(null);
        return this;
    }

    public void setClosingTimes(Set<ClosingTime> closingTimes) {
        if (this.closingTimes != null) {
            this.closingTimes.forEach(i -> i.setOrganization(null));
        }
        if (closingTimes != null) {
            closingTimes.forEach(i -> i.setOrganization(this));
        }
        this.closingTimes = closingTimes;
    }

    public Set<OpeningHour> getOpeningHours() {
        return this.openingHours;
    }

    public Organization openingHours(Set<OpeningHour> openingHours) {
        this.setOpeningHours(openingHours);
        return this;
    }

    public Organization addOpeningHour(OpeningHour openingHour) {
        this.openingHours.add(openingHour);
        openingHour.setOrganization(this);
        return this;
    }

    public Organization removeOpeningHour(OpeningHour openingHour) {
        this.openingHours.remove(openingHour);
        openingHour.setOrganization(null);
        return this;
    }

    public void setOpeningHours(Set<OpeningHour> openingHours) {
        if (this.openingHours != null) {
            this.openingHours.forEach(i -> i.setOrganization(null));
        }
        if (openingHours != null) {
            openingHours.forEach(i -> i.setOrganization(this));
        }
        this.openingHours = openingHours;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public Organization employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public Organization addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setOrganization(this);
        return this;
    }

    public Organization removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setOrganization(null);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        if (this.employees != null) {
            this.employees.forEach(i -> i.setOrganization(null));
        }
        if (employees != null) {
            employees.forEach(i -> i.setOrganization(this));
        }
        this.employees = employees;
    }

    public Addresse getAddresse() {
        return this.addresse;
    }

    public Organization addresse(Addresse addresse) {
        this.setAddresse(addresse);
        return this;
    }

    public void setAddresse(Addresse addresse) {
        if (this.addresse != null) {
            this.addresse.setOrganization(null);
        }
        if (addresse != null) {
            addresse.setOrganization(this);
        }
        this.addresse = addresse;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return this.id != null && this.id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organization{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", mail='" + getMail() + "'" +
                ", phone='" + getPhone() + "'" +
                ", ownerId='" + getOwnerId() + "'" +
                ", active='" + getActive() + "'" +
                "}";
    }
}
