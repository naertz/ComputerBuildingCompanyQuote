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

import computerbuilding.beans.ThermalCompound;
import computerbuilding.service.ThermalCompoundServiceInterface;

@Controller
public class ThermalCompoundController {
	@Autowired
	ThermalCompoundServiceInterface thermalCompoundService;

	@GetMapping("/thermal_compounds")
	public String viewThermalCompoundsPage(Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/thermal_compounds/add_thermal_compound")
	public String addThermalCompound(Model model) {
		ThermalCompound thermalCompound  = new ThermalCompound();
		model.addAttribute("thermalCompound", thermalCompound);
		return "new_thermal_compound";
	}

	@PostMapping("/thermal_compounds/update_thermal_compound")
	public String updateThermalCompound(@ModelAttribute("thermalCompound") ThermalCompound thermalCompound) {
		thermalCompoundService.updateThermalCompound(thermalCompound);
		return "redirect:/thermal_compounds";
	}

	@GetMapping("/thermal_compounds/edit_thermal_compound/{id}")
	public String editThermalCompound(@PathVariable(value="id") long id, Model model) {
		ThermalCompound thermalCompound = thermalCompoundService.getThermalCompoundById(id);
		model.addAttribute("thermalCompound", thermalCompound);
		return "edit_thermal_compound";
	}

	@GetMapping("/thermal_compounds/delete_thermal_compound/{id}")
	public String deleteThermalCompound(@PathVariable(value="id") long id) {
		this.thermalCompoundService.deleteThermalCompoundById(id);
		return "redirect:/thermal_compounds";
	}

	@GetMapping("/thermal_compounds/page/{pageNumber}")
	public String findPaginated(@PathVariable(value="pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection, Model model) {
		int pageSize = 5;

		Page<ThermalCompound> thermalCompoundPage = thermalCompoundService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<ThermalCompound> thermalCompounds = thermalCompoundPage.getContent();

		model.addAttribute("thermalCompoundPage", pageNumber);
		model.addAttribute("totalThermalCompoundPages", thermalCompoundPage.getTotalPages());
		model.addAttribute("totalThermalCompoundItems", thermalCompoundPage.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("thermalCompounds", thermalCompounds);
		return "thermal_compounds";
	}
}