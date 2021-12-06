/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 5, 2021
 */
package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.Monitor;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {}
