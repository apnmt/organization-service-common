package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.Addresse;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Addresse} entity.
 */
public class AddresseDTO implements Serializable {

    private static final long serialVersionUID = 6884589124862883566L;
    private Long id;

    @NotNull
    private String line1;

    @NotNull
    private String city;

    @NotNull
    private String postalCode;

    @NotNull
    private String country;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
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
                "}";
    }
}
