package computerbuilding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import computerbuilding.beans.ThComp;
import computerbuilding.service.ThCompServiceInterface;



public class ThCompController {
	@Autowired
	ThCompServiceInterface thcompService;

	@GetMapping("/thcomps")
	public String viewthcompPage(Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/thcomps/add_thcomp")
	public String addthcomp(Model model) {
		ThComp thcomp  = new ThComp();
		model.addAttribute("thcomp", thcomp);
		return "new_thcomp";
	}

	@PostMapping("/thcomps/update_thcomp")
	public String updatethcomp(@ModelAttribute("thcomp") ThComp thcomp) {
		thcompService.updateThComp(thcomp);
		return "redirect:/thcomp";
	}

	@GetMapping("/thcomps/edit_thcomp/{id}")
	public String editthcomp(@PathVariable(value="id") long id, Model model) {
		ThComp thcomp = thcompService.getThCompById(id);
		model.addAttribute("thcomp", thcomp);
		return "edit_thcomp";
	}

	@GetMapping("/thcomps/delete_thcomp/{id}")
	public String deletethcomp(@PathVariable(value="id") long id) {
		this.thcompService.deleteThCompById(id);
		return "redirect:/thcomp";
	}

	@GetMapping("/thcomps/page/{pageNumber}")
	public String findPaginated(@PathVariable(value="pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection, Model model) {
		int pageSize = 5;

		Page<ThComp> thcompPage = thcompService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<ThComp> thcomp = thcompPage.getContent();

		model.addAttribute("thcompPage", pageNumber);
		model.addAttribute("totalthcompPages", thcompPage.getTotalPages());
		model.addAttribute("totalthcompItems", thcompPage.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("thcomp", thcomp);
		return "thcomp";
	}
}
