package de.apnmt.organization.common.repository;

import de.apnmt.organization.common.domain.Employee;
import de.apnmt.organization.common.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByOrganization(Organization organization);

}
