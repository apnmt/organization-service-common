package de.apnmt.organization.common.repository;

import de.apnmt.organization.common.domain.ClosingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ClosingTime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClosingTimeRepository extends JpaRepository<ClosingTime, Long> {}
