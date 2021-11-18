package computerbuilding.repository;

/**
 *@author Jake Soulinthavong - jakesoul
 *CIS175 - Fall 2021
 *September 23, 2021
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.CPU;

@Repository
public interface CPURepository extends JpaRepository<CPU, Long> { }

