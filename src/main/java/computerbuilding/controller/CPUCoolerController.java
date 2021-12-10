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

import computerbuilding.beans.CPUCooler;
import computerbuilding.service.CPUCoolerServiceInterface;

@Controller
public class CPUCoolerController {
	@Autowired
	CPUCoolerServiceInterface cPUCoolerService;

	@GetMapping("/cpu_coolers")
	public String viewCPUCoolerPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/cpu_coolers/add_cpu_cooler")
	public String addCPUCooler(final Model model) {
		final CPUCooler cPUCooler = new CPUCooler();
		model.addAttribute("cPUCooler", cPUCooler);
		return "new_cpu_cooler";
	}

	@PostMapping("/cpu_coolers/update_cpu_cooler")
	public String updateCPU(@ModelAttribute("cPUCooler") final CPUCooler cPUCooler) {
		cPUCoolerService.updateCPUCooler(cPUCooler);
		return "redirect:/cpu_coolers";
	}

	@GetMapping("/cpu_coolers/edit_cpu_cooler/{id}")
	public String editCPUCooler(@PathVariable(value = "id") final long id, final Model model) {
		final CPUCooler cPUCooler = cPUCoolerService.getCPUCoolerById(id);
		model.addAttribute("cPUCooler", cPUCooler);
		return "edit_cpu_cooler";
	}

	@GetMapping("/cpu_coolers/delete_cpu_cooler/{id}")
	public String deleteCPUCooler(@PathVariable(value = "id") final long id) {
		cPUCoolerService.deleteCPUCoolerById(id);
		return "redirect:/cpu_coolers";
	}

	@GetMapping("/cpu_coolers/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<CPUCooler> cPUCoolerPage = cPUCoolerService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<CPUCooler> cPUCooler = cPUCoolerPage.getContent();
		model.addAttribute("cPUCoolerPage", pageNumber);
		model.addAttribute("totalCPUCoolerPages", cPUCoolerPage.getTotalPages());
		model.addAttribute("totalCPUCoolerItems", cPUCoolerPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("cPUCoolers", cPUCooler);
		return "cpu_coolers";
	}
}
