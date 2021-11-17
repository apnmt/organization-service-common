package de.apnmt.organization.common.repository;

import de.apnmt.organization.common.domain.Addresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Addresse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddresseRepository extends JpaRepository<Addresse, Long> {}
