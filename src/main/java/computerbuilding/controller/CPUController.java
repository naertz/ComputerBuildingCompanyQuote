/**
 * @author Jake Soulinthavong - jakesoul
 * CIS175 - Fall 2021
 * September 23, 2021
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

import computerbuilding.beans.CPU;
import computerbuilding.service.CPUServiceInterface;

@Controller
public class CPUController {
	@Autowired
	CPUServiceInterface cPUService;

	@GetMapping("/cpus")
	public String viewCPUsPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/cpus/add_cpu")
	public String addCPU(final Model model) {
		final CPU cPU = new CPU();
		model.addAttribute("cPU", cPU);
		return "new_cpu";
	}

	@PostMapping("/cpus/update_cpu")
	public String updateCPU(@ModelAttribute("cPU") final CPU cPU) {
		cPUService.updateCPU(cPU);
		return "redirect:/cpus";
	}

	@GetMapping("/cpus/edit_cpu/{id}")
	public String editCPU(@PathVariable(value = "id") final long id, final Model model) {
		final CPU cPU = cPUService.getCPUById(id);
		model.addAttribute("cPU", cPU);
		return "edit_cpu";
	}

	@GetMapping("/cpus/delete_cpu/{id}")
	public String deleteCPU(@PathVariable(value = "id") final long id) {
		cPUService.deleteCPUById(id);
		return "redirect:/cpus";
	}

	@GetMapping("/cpus/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<CPU> cPUPage = cPUService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<CPU> cPUs = cPUPage.getContent();
		model.addAttribute("cPUPage", pageNumber);
		model.addAttribute("totalCPUPages", cPUPage.getTotalPages());
		model.addAttribute("totalCPUItems", cPUPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("cPUs", cPUs);
		return "cpus";
	}
}