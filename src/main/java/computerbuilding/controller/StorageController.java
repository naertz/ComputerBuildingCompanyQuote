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

import computerbuilding.beans.Storage;

import computerbuilding.service.StorageServiceInterface;

@Controller
public class StorageController {
	@Autowired
	StorageServiceInterface sTORAGEService;

	@GetMapping("/storage")
	public String viewStoragePage(Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/storage/add_storage")
	public String addStorage(Model model) {
		Storage sTORAGE  = new Storage();
		model.addAttribute("sTORAGE", sTORAGE);
		return "new_storage";
	}

	@PostMapping("/storage/update_storage")
	public String updateStorage(@ModelAttribute("sTORAGE") Storage sTORAGE) {
		sTORAGEService.updateStorage(sTORAGE);
		return "redirect:/storage";
	}

	@GetMapping("/storage/edit_storage/{id}")
	public String editStorage(@PathVariable(value="id") long id, Model model) {
		Storage sTORAGE = sTORAGEService.getStorageById(id);
		model.addAttribute("sTORAGE", sTORAGE);
		return "edit_storage";
	}

	@GetMapping("/stroage/delete_storage/{id}")
	public String deleteStorage(@PathVariable(value="id") long id) {
		this.sTORAGEService.deleteStorageById(id);
		return "redirect:/storage";
	}

	@GetMapping("/storage/page/{pageNumber}")
	public String findPaginated(@PathVariable(value="pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection, Model model) {
		int pageSize = 5;

		Page<Storage> sTORAGEPage = sTORAGEService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<Storage> sTORAGE = sTORAGEPage.getContent();

		model.addAttribute("sTORAGEPage", pageNumber);
		model.addAttribute("totalStoragePages", sTORAGEPage.getTotalPages());
		model.addAttribute("totalStorageItems", sTORAGEPage.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		model.addAttribute("sTORAGE", sTORAGE);
		return "storage";
	}
}