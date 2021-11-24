/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 23, 2021
 */

package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.CustomerQuoteFulfillment;

@Repository
public interface CustomerQuoteFulfillmentRepository extends JpaRepository<CustomerQuoteFulfillment, Long> { }
