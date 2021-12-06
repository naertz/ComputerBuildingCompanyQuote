/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 23, 2021
 */
package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.CustomerQuoteFulfillment;

public interface CustomerQuoteFulfillmentServiceInterface {
	List<CustomerQuoteFulfillment> getAllCustomerQuoteFulfillments();

	void updateCustomerQuoteFulfillment(CustomerQuoteFulfillment customerQuoteFulfillment);

	CustomerQuoteFulfillment getCustomerQuoteFulfillmentById(long id);

	void deleteCustomerQuoteFulfillmentById(long id);

	Page<CustomerQuoteFulfillment> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
