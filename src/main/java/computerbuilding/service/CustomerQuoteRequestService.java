/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
 */
package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import computerbuilding.beans.CustomerQuoteRequest;
import computerbuilding.repository.CustomerQuoteRequestRepository;

@Service
public class CustomerQuoteRequestService implements CustomerQuoteRequestServiceInterface {
	@Autowired
	private CustomerQuoteRequestRepository customerQuoteRequestRepository;

	@Override
	public List<CustomerQuoteRequest> getAllCustomerQuoteRequests() {
		return customerQuoteRequestRepository.findAll();
	}

	@Override
	public void updateCustomerQuoteRequest(final CustomerQuoteRequest customerQuoteRequest) {
		customerQuoteRequestRepository.save(customerQuoteRequest);
	}

	@Override
	public CustomerQuoteRequest getCustomerQuoteRequestById(final long id) {
		final Optional<CustomerQuoteRequest> optional = customerQuoteRequestRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Customer Quote Request not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteCustomerQuoteRequestById(final long id) {
		customerQuoteRequestRepository.deleteById(id);
	}

	@Override
	public Page<CustomerQuoteRequest> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return customerQuoteRequestRepository.findAll(pageable);
	}
}