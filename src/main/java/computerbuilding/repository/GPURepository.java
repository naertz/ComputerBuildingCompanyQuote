/**
 * @author Jake Soulinthavong - jakesoul
 * CIS175 - Fall 2021
 * September 23, 2021
 */
package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.GPU;

@Repository
public interface GPURepository extends JpaRepository<GPU, Long> {}
