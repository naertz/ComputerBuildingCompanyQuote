/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 5, 2021
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

import computerbuilding.beans.PSU;
import computerbuilding.service.PSUServiceInterface;

@Controller
public class PSUController {
	@Autowired
	PSUServiceInterface pSUService;

	@GetMapping("/psus")
	public String viewPSUsPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/psus/add_psu")
	public String addPSU(final Model model) {
		final PSU pSU = new PSU();
		model.addAttribute("pSU", pSU);
		return "new_psu";
	}

	@PostMapping("/psus/update_psu")
	public String updatePSU(@ModelAttribute("pSU") final PSU pSU) {
		pSUService.updatePSU(pSU);
		return "redirect:/psus";
	}

	@GetMapping("/psus/edit_psu/{id}")
	public String editPSU(@PathVariable(value = "id") final long id, final Model model) {
		final PSU pSU = pSUService.getPSUById(id);
		model.addAttribute("pSU", pSU);
		return "edit_psu";
	}

	@GetMapping("/psus/delete_psu/{id}")
	public String deletePSU(@PathVariable(value = "id") final long id) {
		pSUService.deletePSUById(id);
		return "redirect:/psus";
	}

	@GetMapping("/psus/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<PSU> pSUPage = pSUService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<PSU> pSUs = pSUPage.getContent();
		model.addAttribute("pSUPage", pageNumber);
		model.addAttribute("totalPSUPages", pSUPage.getTotalPages());
		model.addAttribute("totalPSUItems", pSUPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("pSUs", pSUs);
		return "psus";
	}
}
