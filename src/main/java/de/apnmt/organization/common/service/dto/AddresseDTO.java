package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.Addresse;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Addresse} entity.
 */
public class AddresseDTO implements Serializable {

    private Long id;

    @NotNull
    private String line1;

    @NotNull
    private String city;

    @NotNull
    private String postalCode;

    @NotNull
    private String country;

    private OrganizationDTO organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        if (!(o instanceof AddresseDTO)) {
            return false;
        }

        AddresseDTO addresseDTO = (AddresseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, addresseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddresseDTO{" +
            "id=" + getId() +
            ", line1='" + getLine1() + "'" +
            ", city='" + getCity() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", country='" + getCountry() + "'" +
            ", organization=" + getOrganization() +
            "}";
    }
}
