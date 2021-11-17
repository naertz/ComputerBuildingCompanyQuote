/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
 */

package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.CustomerQuoteRequest;

@Repository
public interface CustomerQuoteRequestRepository extends JpaRepository<CustomerQuoteRequest, Long> { }
