/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 10, 2021
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

import computerbuilding.beans.Mouse;
import computerbuilding.service.MouseServiceInterface;

@Controller
public class MouseController {
	@Autowired
	MouseServiceInterface mouseService;

	@GetMapping("/mice")
	public String viewMicePage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/mice/add_mouse")
	public String addMouse(final Model model) {
		final Mouse mouse = new Mouse();
		model.addAttribute("mouse", mouse);
		return "new_mouse";
	}

	@PostMapping("/mice/update_mouse")
	public String updateMouse(@ModelAttribute("mouse") final Mouse mouse) {
		mouseService.updateMouse(mouse);
		return "redirect:/mice";
	}

	@GetMapping("/mice/edit_mouse/{id}")
	public String editMouse(@PathVariable(value = "id") final long id, final Model model) {
		final Mouse mouse = mouseService.getMouseById(id);
		model.addAttribute("mouse", mouse);
		return "edit_mouse";
	}

	@GetMapping("/mice/delete_mouse/{id}")
	public String deleteMouse(@PathVariable(value = "id") final long id) {
		mouseService.deleteMouseById(id);
		return "redirect:/mice";
	}

	@GetMapping("/mice/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<Mouse> mousePage = mouseService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<Mouse> mice = mousePage.getContent();
		model.addAttribute("mousePage", pageNumber);
		model.addAttribute("totalMousePages", mousePage.getTotalPages());
		model.addAttribute("totalMouseItems", mousePage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("mice", mice);
		return "mice";
	}
}
