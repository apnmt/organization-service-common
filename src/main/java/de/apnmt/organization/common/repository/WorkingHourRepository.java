package de.apnmt.organization.common.repository;

import de.apnmt.organization.common.domain.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkingHour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkingHourRepository extends JpaRepository<WorkingHour, Long> {

    void deleteAllByIdGreaterThan(Long id);

}
