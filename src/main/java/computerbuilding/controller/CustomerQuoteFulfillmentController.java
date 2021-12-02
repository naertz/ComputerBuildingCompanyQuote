/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 23, 2021
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

import computerbuilding.beans.CustomerQuoteFulfillment;
import computerbuilding.service.CPUServiceInterface;
import computerbuilding.service.CustomerQuoteFulfillmentServiceInterface;
import computerbuilding.service.CustomerQuoteRequestServiceInterface;
import computerbuilding.service.GPUServiceInterface;
import computerbuilding.service.MotherboardServiceInterface;
import computerbuilding.service.RAMServiceInterface;

@Controller
public class CustomerQuoteFulfillmentController {
	@Autowired
	CustomerQuoteFulfillmentServiceInterface customerQuoteFulfillmentService;
	@Autowired
	CustomerQuoteRequestServiceInterface customerQuoteRequestService;
	@Autowired
	MotherboardServiceInterface motherboardService;
	@Autowired
	CPUServiceInterface cPUService;
	@Autowired
	RAMServiceInterface rAMService;
	@Autowired
	GPUServiceInterface gPUService;

	@GetMapping("/fulfillments")
	public String viewFulfillmentsPage(Model model) {
		return findPaginated(1, "id", "asc", model);
	}

	@GetMapping("/fulfillments/create_fulfillment")
	public String createCustomerQuoteFulfillment(Model model) {
		CustomerQuoteFulfillment customerQuoteFulfillment = new CustomerQuoteFulfillment();
		model.addAttribute("customerQuoteFulfillment", customerQuoteFulfillment);
		model.addAttribute("customerQuoteRequests", customerQuoteRequestService.getAllCustomerQuoteRequests());
		model.addAttribute("motherboards", motherboardService.getAllMotherboards());
		model.addAttribute("cPUS", cPUService.getAllCPUs());
		model.addAttribute("rAM", rAMService.getAllRAM());
		model.addAttribute("gPUS", gPUService.getAllGPUs());
		return "new_fulfillment";
	}

	@PostMapping("/fulfillments/update_fulfillment")
	public String updateCustomerQuoteFulfillment(@ModelAttribute("customerQuoteFulfillment") CustomerQuoteFulfillment customerQuoteFulfillment) {
		customerQuoteFulfillmentService.updateCustomerQuoteFulfillment(customerQuoteFulfillment);
		return "redirect:/fulfillments";
	}

	@GetMapping("/fulfillments/confirm_fulfillment/{id}")
	public String confirmCustomerQuoteFulfillment(@PathVariable(value="id") long id, Model model) {
		CustomerQuoteFulfillment customerQuoteFulfillment = customerQuoteFulfillmentService.getCustomerQuoteFulfillmentById(id);
		model.addAttribute("customerQuoteFulfillment", customerQuoteFulfillment);
		return "confirm_fulfillment";
	}

	@GetMapping("/fulfillments/edit_fulfillment/{id}")
	public String editCustomerQuoteFulfillment(@PathVariable(value="id") long id, Model model) {
		CustomerQuoteFulfillment customerQuoteFulfillment = customerQuoteFulfillmentService.getCustomerQuoteFulfillmentById(id);
		model.addAttribute("customerQuoteFulfillment", customerQuoteFulfillment);
		model.addAttribute("customerQuoteRequests", customerQuoteRequestService.getAllCustomerQuoteRequests());
		model.addAttribute("motherboards", motherboardService.getAllMotherboards());
		model.addAttribute("cPUS", cPUService.getAllCPUs());
		model.addAttribute("rAM", rAMService.getAllRAM());
		model.addAttribute("gPUS", gPUService.getAllGPUs());
		return "edit_fulfillment";
	}

	@GetMapping("/fulfillments/delete_fulfillment/{id}")
	public String deleteCustomerQuoteFulfillment(@PathVariable(value="id") long id) {
		this.customerQuoteFulfillmentService.deleteCustomerQuoteFulfillmentById(id);
		return "redirect:/fulfillments";
	}

	@GetMapping("/fulfillments/page/{pageNumber}")
	public String findPaginated(@PathVariable(value="pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection, Model model) {
		int pageSize = 5;

		Page<CustomerQuoteFulfillment> fulfillmentPage = customerQuoteFulfillmentService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<CustomerQuoteFulfillment> fulfillments = fulfillmentPage.getContent();

		model.addAttribute("fulfillmentPage", pageNumber);
		model.addAttribute("totalFulfillmentPages", fulfillmentPage.getTotalPages());
		model.addAttribute("totalFulfillmentItems", fulfillmentPage.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("customerQuoteFulfillments", fulfillments);
		return "fulfillments";
	}
}
