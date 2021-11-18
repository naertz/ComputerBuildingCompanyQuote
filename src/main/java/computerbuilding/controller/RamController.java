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

import computerbuilding.beans.Ram;
import computerbuilding.service.RamServiceInterface;

@Controller
public class RamController {
	@Autowired
	RamServiceInterface ramService;
	
	@GetMapping("/ram")
	public String viewRamPage(Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/ram/add_ram")
	public String addRam(Model model) {
		Ram ram  = new Ram();
		model.addAttribute("ram", ram);
		return "new_ram";
	}

	@PostMapping("/ram/update_ram")
	public String updateRam(@ModelAttribute("ram") Ram ram) {
		ramService.updateRam(ram);
		return "redirect:/ram";
	}

	@GetMapping("/ram/edit_ram/{id}")
	public String editRam(@PathVariable(value="id") long id, Model model) {
		Ram ram = ramService.getRamById(id);
		model.addAttribute("ram", ram);
		return "edit_ram";
	}

	@GetMapping("/ram/delete_ram/{id}")
	public String deleteRam(@PathVariable(value="id") long id) {
		this.ramService.deleteRamById(id);
		return "redirect:/ram";
	}

	@GetMapping("/ram/page/{pageNumber}")
	public String findPaginated(@PathVariable(value="pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection, Model model) {
		int pageSize = 5;

		Page<Ram> ramPage = ramService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<Ram> ram = ramPage.getContent();

		model.addAttribute("ramPage", pageNumber);
		model.addAttribute("totalRamPages", ramPage.getTotalPages());
		model.addAttribute("totalRamItems", ramPage.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("ram", ram);
		return "ram";
	}
}
