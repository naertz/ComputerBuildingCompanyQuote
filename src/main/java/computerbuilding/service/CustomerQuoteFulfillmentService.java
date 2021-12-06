/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 23, 2021
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

import computerbuilding.beans.CustomerQuoteFulfillment;
import computerbuilding.repository.CustomerQuoteFulfillmentRepository;

@Service
public class CustomerQuoteFulfillmentService implements CustomerQuoteFulfillmentServiceInterface {
	@Autowired
	private CustomerQuoteFulfillmentRepository customerQuoteFulfillmentRepository;

	@Override
	public List<CustomerQuoteFulfillment> getAllCustomerQuoteFulfillments() {
		return customerQuoteFulfillmentRepository.findAll();
	}

	@Override
	public void updateCustomerQuoteFulfillment(final CustomerQuoteFulfillment customerQuoteFulfillment) {
		customerQuoteFulfillmentRepository.save(customerQuoteFulfillment);
	}

	@Override
	public CustomerQuoteFulfillment getCustomerQuoteFulfillmentById(final long id) {
		final Optional<CustomerQuoteFulfillment> optional = customerQuoteFulfillmentRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Customer Quote Fulfillment not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteCustomerQuoteFulfillmentById(final long id) {
		customerQuoteFulfillmentRepository.deleteById(id);
	}

	@Override
	public Page<CustomerQuoteFulfillment> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return customerQuoteFulfillmentRepository.findAll(pageable);
	}
}