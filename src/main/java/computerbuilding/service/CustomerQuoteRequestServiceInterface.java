/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
 */

package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.CustomerQuoteRequest;

public interface CustomerQuoteRequestServiceInterface {
	List<CustomerQuoteRequest> getAllCustomerQuoteRequests();
	void updateCustomerQuoteRequest(CustomerQuoteRequest customerQuoteRequest);
	CustomerQuoteRequest getCustomerQuoteRequestById(long id);
	void deleteCustomerQuoteRequestById(long id);
	Page<CustomerQuoteRequest> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
