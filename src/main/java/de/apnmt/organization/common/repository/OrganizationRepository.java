package de.apnmt.organization.common.repository;

import de.apnmt.organization.common.domain.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Organization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Page<Organization> findAllByActive(boolean active, Pageable pageable);

    void deleteAllByIdGreaterThan(Long id);

}
