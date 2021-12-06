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

import computerbuilding.beans.Monitor;
import computerbuilding.service.MonitorServiceInterface;

@Controller
public class MonitorController {
	@Autowired
	MonitorServiceInterface monitorService;

	@GetMapping("/monitors")
	public String viewMonitorsPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/monitors/add_monitor")
	public String addMonitor(final Model model) {
		final Monitor monitor = new Monitor();
		model.addAttribute("monitor", monitor);
		return "new_monitor";
	}

	@PostMapping("/monitors/update_monitor")
	public String updateMonitor(@ModelAttribute("monitor") final Monitor monitor) {
		monitorService.updateMonitor(monitor);
		return "redirect:/monitors";
	}

	@GetMapping("/monitors/edit_monitor/{id}")
	public String editMonitor(@PathVariable(value = "id") final long id, final Model model) {
		final Monitor monitor = monitorService.getMonitorById(id);
		model.addAttribute("monitor", monitor);
		return "edit_monitor";
	}

	@GetMapping("/monitors/delete_monitor/{id}")
	public String deleteMonitor(@PathVariable(value = "id") final long id) {
		monitorService.deleteMonitorById(id);
		return "redirect:/monitors";
	}

	@GetMapping("/monitors/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<Monitor> monitorPage = monitorService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<Monitor> monitors = monitorPage.getContent();
		model.addAttribute("monitorPage", pageNumber);
		model.addAttribute("totalMonitorPages", monitorPage.getTotalPages());
		model.addAttribute("totalMonitorItems", monitorPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("monitors", monitors);
		return "monitors";
	}
}
