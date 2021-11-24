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
	public void updateCustomerQuoteFulfillment(CustomerQuoteFulfillment customerQuoteFulfillment) {
		this.customerQuoteFulfillmentRepository.save(customerQuoteFulfillment);
	}

	@Override
	public CustomerQuoteFulfillment getCustomerQuoteFulfillmentById(long id) {
		Optional<CustomerQuoteFulfillment> optional = customerQuoteFulfillmentRepository.findById(id);
		CustomerQuoteFulfillment customerQuoteFulfillment = null;
		if (optional.isPresent()) {
			customerQuoteFulfillment = optional.get();
		} else {
			throw new RuntimeException("CustomerQuoteFulfillment not found for id: " + id);
		}
		return customerQuoteFulfillment;
	}

	@Override
	public void deleteCustomerQuoteFulfillmentById(long id) {
			this.customerQuoteFulfillmentRepository.deleteById(id);
	}

	@Override
	public Page<CustomerQuoteFulfillment> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.customerQuoteFulfillmentRepository.findAll(pageable);
	}
}
