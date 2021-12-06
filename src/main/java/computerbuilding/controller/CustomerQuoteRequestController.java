/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
 */
package computerbuilding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import computerbuilding.beans.CustomerQuoteRequest;
import computerbuilding.service.CustomerQuoteRequestServiceInterface;

@Controller
public class CustomerQuoteRequestController {
	@Autowired
	CustomerQuoteRequestServiceInterface customerQuoteRequestService;

	@GetMapping("/")
	public String viewHomePage(final Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/create_request")
	public String createCustomerQuoteRequest(final Model model) {
		final CustomerQuoteRequest customerQuoteRequest = new CustomerQuoteRequest();
		model.addAttribute("customerQuoteRequest", customerQuoteRequest);
		return "new_request";
	}

	@PostMapping("/update_request")
	public String updateCustomerQuoteRequest(@ModelAttribute("customerQuoteRequest") final CustomerQuoteRequest customerQuoteRequest) {
		customerQuoteRequestService.updateCustomerQuoteRequest(customerQuoteRequest);
		return "redirect:/";
	}

	@GetMapping("/edit_request/{id}")
	public String editCustomerQuoteRequest(@PathVariable(value = "id") final long id, final Model model) {
		final CustomerQuoteRequest customerQuoteRequest = customerQuoteRequestService.getCustomerQuoteRequestById(id);
		model.addAttribute("customerQuoteRequest", customerQuoteRequest);
		return "edit_request";
	}

	@GetMapping("/delete_request/{id}")
	public String deleteCustomerQuoteRequest(@PathVariable(value = "id") final long id) {
		customerQuoteRequestService.deleteCustomerQuoteRequestById(id);
		return "redirect:/";
	}

	@GetMapping("/request_page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<CustomerQuoteRequest> requestPage = customerQuoteRequestService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<CustomerQuoteRequest> requests = requestPage.getContent();
		model.addAttribute("requestPage", pageNumber);
		model.addAttribute("totalRequestPages", requestPage.getTotalPages());
		model.addAttribute("totalRequestItems", requestPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("customerQuoteRequests", requests);
		return "requests";
	}
}
