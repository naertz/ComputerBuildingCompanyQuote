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
	public void updateCustomerQuoteRequest(CustomerQuoteRequest customerQuoteRequest) {
		this.customerQuoteRequestRepository.save(customerQuoteRequest);
	}

	@Override
	public CustomerQuoteRequest getCustomerQuoteRequestById(long id) {
		Optional<CustomerQuoteRequest> optional = customerQuoteRequestRepository.findById(id);
		CustomerQuoteRequest customerQuoteRequest = null;
		if (optional.isPresent()) {
			customerQuoteRequest = optional.get();
		} else {
			throw new RuntimeException("CustomerQuoteRequest not found for id: " + id);
		}
		return customerQuoteRequest;
	}

	@Override
	public void deleteCustomerQuoteRequestById(long id) {
			this.customerQuoteRequestRepository.deleteById(id);
	}

	@Override
	public Page<CustomerQuoteRequest> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.customerQuoteRequestRepository.findAll(pageable);
	}

}
