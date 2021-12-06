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

import computerbuilding.beans.Motherboard;
import computerbuilding.service.MotherboardServiceInterface;

@Controller
public class MotherboardController {
	@Autowired
	MotherboardServiceInterface motherboardService;

	@GetMapping("/motherboards")
	public String viewMotherboardsPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/motherboards/add_motherboard")
	public String addMotherboard(final Model model) {
		final Motherboard motherboard = new Motherboard();
		model.addAttribute("motherboard", motherboard);
		return "new_motherboard";
	}

	@PostMapping("/motherboards/update_motherboard")
	public String updateMotherboard(@ModelAttribute("motherboard") final Motherboard motherboard) {
		motherboardService.updateMotherboard(motherboard);
		return "redirect:/motherboards";
	}

	@GetMapping("/motherboards/edit_motherboard/{id}")
	public String editMotherboard(@PathVariable(value = "id") final long id, final Model model) {
		final Motherboard motherboard = motherboardService.getMotherboardById(id);
		model.addAttribute("motherboard", motherboard);
		return "edit_motherboard";
	}

	@GetMapping("/motherboards/delete_motherboard/{id}")
	public String deleteMotherboard(@PathVariable(value = "id") final long id) {
		motherboardService.deleteMotherboardById(id);
		return "redirect:/motherboards";
	}

	@GetMapping("/motherboards/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<Motherboard> motherboardPage = motherboardService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<Motherboard> motherboards = motherboardPage.getContent();
		model.addAttribute("motherboardPage", pageNumber);
		model.addAttribute("totalMotherboardPages", motherboardPage.getTotalPages());
		model.addAttribute("totalMotherboardItems", motherboardPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("motherboards", motherboards);
		return "motherboards";
	}
}
