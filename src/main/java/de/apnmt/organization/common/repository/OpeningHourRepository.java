package de.apnmt.organization.common.repository;

import de.apnmt.organization.common.domain.OpeningHour;
import de.apnmt.organization.common.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the OpeningHour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpeningHourRepository extends JpaRepository<OpeningHour, Long> {

    List<OpeningHour> findAllByOrganization(Organization organization);

}
