package de.apnmt.organization.common.repository;

import de.apnmt.organization.domain.OpeningHour;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OpeningHour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpeningHourRepository extends JpaRepository<OpeningHour, Long> {}
