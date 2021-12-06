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

import computerbuilding.beans.GPU;
import computerbuilding.service.GPUServiceInterface;

@Controller
public class GPUController {
	@Autowired
	GPUServiceInterface gPUService;

	@GetMapping("/gpus")
	public String viewGPUsPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/gpus/add_gpu")
	public String addGPU(final Model model) {
		final GPU gPU = new GPU();
		model.addAttribute("gPU", gPU);
		return "new_gpu";
	}

	@PostMapping("/gpus/update_gpu")
	public String updateGPU(@ModelAttribute("gpu") final GPU gPU) {
		gPUService.updateGPU(gPU);
		return "redirect:/gpus";
	}

	@GetMapping("/gpus/edit_gpu/{id}")
	public String editGPU(@PathVariable(value = "id") final long id, final Model model) {
		final GPU gPU = gPUService.getGPUById(id);
		model.addAttribute("gPU", gPU);
		return "edit_gpu";
	}

	@GetMapping("/gpus/delete_gpu/{id}")
	public String deleteGPU(@PathVariable(value = "id") final long id) {
		gPUService.deleteGPUById(id);
		return "redirect:/gpus";
	}

	@GetMapping("/gpus/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<GPU> gPUPage = gPUService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<GPU> gPUs = gPUPage.getContent();
		model.addAttribute("gPUPage", pageNumber);
		model.addAttribute("totalGPUPages", gPUPage.getTotalPages());
		model.addAttribute("totalGPUItems", gPUPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("gPUs", gPUs);
		return "gpus";
	}
}