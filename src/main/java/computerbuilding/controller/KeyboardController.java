/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 6, 2021
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

import computerbuilding.beans.Keyboard;
import computerbuilding.service.KeyboardServiceInterface;

@Controller
public class KeyboardController {
	@Autowired
	KeyboardServiceInterface keyboardService;

	@GetMapping("/keyboards")
	public String viewKeyboardsPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/keyboards/add_keyboard")
	public String addKeyboard(final Model model) {
		final Keyboard keyboard = new Keyboard();
		model.addAttribute("keyboard", keyboard);
		return "new_keyboard";
	}

	@PostMapping("/keyboards/update_keyboard")
	public String updateKeyboard(@ModelAttribute("keyboard") final Keyboard keyboard) {
		keyboardService.updateKeyboard(keyboard);
		return "redirect:/keyboards";
	}

	@GetMapping("/keyboards/edit_keyboard/{id}")
	public String editKeyboard(@PathVariable(value = "id") final long id, final Model model) {
		final Keyboard keyboard = keyboardService.getKeyboardById(id);
		model.addAttribute("keyboard", keyboard);
		return "edit_keyboard";
	}

	@GetMapping("/keyboards/delete_keyboard/{id}")
	public String deleteKeyboard(@PathVariable(value = "id") final long id) {
		keyboardService.deleteKeyboardById(id);
		return "redirect:/keyboards";
	}

	@GetMapping("/keyboards/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<Keyboard> keyboardPage = keyboardService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<Keyboard> keyboards = keyboardPage.getContent();
		model.addAttribute("keyboardPage", pageNumber);
		model.addAttribute("totalKeyboardPages", keyboardPage.getTotalPages());
		model.addAttribute("totalKeyboardItems", keyboardPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("keyboards", keyboards);
		return "keyboards";
	}
}
