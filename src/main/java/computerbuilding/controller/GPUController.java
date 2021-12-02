package computerbuilding.controller;

/**
 *@author Jake Soulinthavong - jakesoul
 *CIS175 - Fall 2021
 *September 23, 2021
 */
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
	GPUServiceInterface gpuService;

	@GetMapping("/gpus")
	public String viewGPUsPage(Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/gpus/add_gpu")
	public String addGPU(Model model) {
		GPU gpu = new GPU();
		model.addAttribute("gpu", gpu);
		return "new_gpu";
	}

	@PostMapping("/gpus/update_gpu")
	public String updateGPU(@ModelAttribute("gpu") GPU gpu) {
		gpuService.updateGPU(gpu);
		return "redirect:/gpus";
	}

	@GetMapping("/gpus/edit_gpu/{id}")
	public String editGPU(@PathVariable(value="id") long id, Model model) {
		GPU gpu = gpuService.getGPUById(id);
		model.addAttribute("gpu", gpu);
		return "edit_gpu";
	}

	@GetMapping("/gpus/delete_gpu/{id}")
	public String deleteGPU(@PathVariable(value="id") long id) {
		this.gpuService.deleteGPUById(id);
		return "redirect:/gpus";
	}

	@GetMapping("/gpus/page/{pageNumber}")
	public String findPaginated(@PathVariable(value="pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection, Model model) {
		int pageSize = 5;

		Page<GPU> gpuPage = gpuService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<GPU> gpus = gpuPage.getContent();

		model.addAttribute("gpuPage", pageNumber);
		model.addAttribute("totalGPUPages", gpuPage.getTotalPages());
		model.addAttribute("totalGPUItems", gpuPage.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("gpus", gpus);
		return "gpus";
	}
}
