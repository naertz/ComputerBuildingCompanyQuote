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

import computerbuilding.beans.CPU;
import computerbuilding.service.CPUServiceInterface;

@Controller
public class CPUController {
	@Autowired
	CPUServiceInterface cpuService;

	@GetMapping("/cpus")
	public String viewCPUsPage(Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/cpus/add_cpu")
	public String addCPU(Model model) {
		CPU cpu = new CPU();
		model.addAttribute("cpu", cpu);
		return "new_cpu";
	}

	@PostMapping("/cpus/update_cpu")
	public String updateCPU(@ModelAttribute("cpu") CPU cpu) {
		cpuService.updateCPU(cpu);
		return "redirect:/cpus";
	}

	@GetMapping("/cpus/edit_cpu/{id}")
	public String editCPU(@PathVariable(value="id") long id, Model model) {
		CPU cpu = cpuService.getCPUById(id);
		model.addAttribute("cpu", cpu);
		return "edit_cpu";
	}

	@GetMapping("/cpus/delete_cpu/{id}")
	public String deleteCPU(@PathVariable(value="id") long id) {
		this.cpuService.deleteCPUById(id);
		return "redirect:/cpus";
	}

	@GetMapping("/cpus/page/{pageNumber}")
	public String findPaginated(@PathVariable(value="pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection, Model model) {
		int pageSize = 5;

		Page<CPU> cpuPage = cpuService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<CPU> cpus = cpuPage.getContent();

		model.addAttribute("cpuPage", pageNumber);
		model.addAttribute("totalCPUPages", cpuPage.getTotalPages());
		model.addAttribute("totalCPUItems", cpuPage.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("cpus", cpus);
		return "cpus";
	}
}

