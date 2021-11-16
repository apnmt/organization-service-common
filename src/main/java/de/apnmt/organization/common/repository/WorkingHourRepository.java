package de.apnmt.organization.common.repository;

import de.apnmt.organization.domain.WorkingHour;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkingHour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkingHourRepository extends JpaRepository<WorkingHour, Long> {}
