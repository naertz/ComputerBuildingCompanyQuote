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
import computerbuilding.service.PSUServiceInterface;
import computerbuilding.service.RAMServiceInterface;
import computerbuilding.service.StorageDeviceServiceInterface;
import computerbuilding.service.ThermalCompoundServiceInterface;

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
	@Autowired
	ThermalCompoundServiceInterface thermalCompoundService;
	@Autowired
	StorageDeviceServiceInterface storageDeviceService;
	@Autowired
	PSUServiceInterface pSUService;

	@GetMapping("/fulfillments")
	public String viewFulfillmentsPage(final Model model) {
		return findPaginated(1, "id", "asc", model);
	}

	@GetMapping("/fulfillments/create_fulfillment")
	public String createCustomerQuoteFulfillment(final Model model) {
		final CustomerQuoteFulfillment customerQuoteFulfillment = new CustomerQuoteFulfillment();
		model.addAttribute("customerQuoteFulfillment", customerQuoteFulfillment);
		model.addAttribute("customerQuoteRequests", customerQuoteRequestService.getAllCustomerQuoteRequests());
		model.addAttribute("motherboards", motherboardService.getAllMotherboards());
		model.addAttribute("cPUs", cPUService.getAllCPUs());
		model.addAttribute("rAM", rAMService.getAllRAM());
		model.addAttribute("gPUs", gPUService.getAllGPUs());
		model.addAttribute("thermalCompounds", thermalCompoundService.getAllThermalCompounds());
		model.addAttribute("storageDevices", storageDeviceService.getAllStorageDevices());
		model.addAttribute("pSUs", pSUService.getAllPSUs());
		return "new_fulfillment";
	}

	@PostMapping("/fulfillments/update_fulfillment")
	public String updateCustomerQuoteFulfillment(@ModelAttribute("customerQuoteFulfillment") final CustomerQuoteFulfillment customerQuoteFulfillment) {
		customerQuoteFulfillmentService.updateCustomerQuoteFulfillment(customerQuoteFulfillment);
		return "redirect:/fulfillments";
	}

	@GetMapping("/fulfillments/confirm_fulfillment/{id}")
	public String confirmCustomerQuoteFulfillment(@PathVariable(value = "id") final long id, final Model model) {
		final CustomerQuoteFulfillment customerQuoteFulfillment = customerQuoteFulfillmentService.getCustomerQuoteFulfillmentById(id);
		model.addAttribute("customerQuoteFulfillment", customerQuoteFulfillment);
		return "confirm_fulfillment";
	}

	@GetMapping("/fulfillments/edit_fulfillment/{id}")
	public String editCustomerQuoteFulfillment(@PathVariable(value = "id") final long id, final Model model) {
		final CustomerQuoteFulfillment customerQuoteFulfillment = customerQuoteFulfillmentService.getCustomerQuoteFulfillmentById(id);
		model.addAttribute("customerQuoteFulfillment", customerQuoteFulfillment);
		model.addAttribute("customerQuoteRequests", customerQuoteRequestService.getAllCustomerQuoteRequests());
		model.addAttribute("motherboards", motherboardService.getAllMotherboards());
		model.addAttribute("cPUs", cPUService.getAllCPUs());
		model.addAttribute("rAM", rAMService.getAllRAM());
		model.addAttribute("gPUs", gPUService.getAllGPUs());
		model.addAttribute("thermalCompounds", thermalCompoundService.getAllThermalCompounds());
		model.addAttribute("storageDevices", storageDeviceService.getAllStorageDevices());
		model.addAttribute("pSUs", pSUService.getAllPSUs());
		return "edit_fulfillment";
	}

	@GetMapping("/fulfillments/delete_fulfillment/{id}")
	public String deleteCustomerQuoteFulfillment(@PathVariable(value = "id") final long id) {
		customerQuoteFulfillmentService.deleteCustomerQuoteFulfillmentById(id);
		return "redirect:/fulfillments";
	}

	@GetMapping("/fulfillments/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<CustomerQuoteFulfillment> fulfillmentPage = customerQuoteFulfillmentService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<CustomerQuoteFulfillment> fulfillments = fulfillmentPage.getContent();
		model.addAttribute("fulfillmentPage", pageNumber);
		model.addAttribute("totalFulfillmentPages", fulfillmentPage.getTotalPages());
		model.addAttribute("totalFulfillmentItems", fulfillmentPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("customerQuoteFulfillments", fulfillments);
		return "fulfillments";
	}
}