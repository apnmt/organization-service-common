package de.apnmt.organization.common.service.dto;

import de.apnmt.organization.common.domain.Organization;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Organization} entity.
 */
public class OrganizationDTO implements Serializable {

    private static final long serialVersionUID = 837849912343249973L;
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String mail;

    @NotNull
    private String phone;

    @NotNull
    private Long ownerId;

    @NotNull
    private Boolean active;

    @NotNull
    private AddresseDTO addresse;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AddresseDTO getAddresse() {
        return this.addresse;
    }

    public void setAddresse(AddresseDTO addresse) {
        this.addresse = addresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizationDTO)) {
            return false;
        }

        OrganizationDTO organizationDTO = (OrganizationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, organizationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "OrganizationDTO{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", mail='" + this.mail + '\'' +
                ", phone='" + this.phone + '\'' +
                ", ownerId=" + this.ownerId +
                ", active=" + this.active +
                ", addresse=" + this.addresse +
                '}';
    }
}
