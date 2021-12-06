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

import computerbuilding.beans.StorageDevice;
import computerbuilding.service.StorageDeviceServiceInterface;

@Controller
public class StorageDeviceController {
	@Autowired
	StorageDeviceServiceInterface storageDeviceService;

	@GetMapping("/storage_devices")
	public String viewStorageDevicesPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/storage_devices/add_storage_device")
	public String addStorageDevice(final Model model) {
		final StorageDevice storageDevice = new StorageDevice();
		model.addAttribute("storageDevice", storageDevice);
		return "new_storage_device";
	}

	@PostMapping("/storage_devices/update_storage_device")
	public String updateStorageDevice(@ModelAttribute("storageDevice") final StorageDevice storageDevice) {
		storageDeviceService.updateStorageDevice(storageDevice);
		return "redirect:/storage_devices";
	}

	@GetMapping("/storage_devices/edit_storage_device/{id}")
	public String editStorageDevice(@PathVariable(value = "id") final long id, final Model model) {
		final StorageDevice storageDevice = storageDeviceService.getStorageDeviceById(id);
		model.addAttribute("storageDevice", storageDevice);
		return "edit_storage_device";
	}

	@GetMapping("/storage_devices/delete_storage_device/{id}")
	public String deleteStorageDevice(@PathVariable(value = "id") final long id) {
		storageDeviceService.deleteStorageDeviceById(id);
		return "redirect:/storage_devices";
	}

	@GetMapping("/storage_devices/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<StorageDevice> storageDevicePage = storageDeviceService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<StorageDevice> storageDevices = storageDevicePage.getContent();
		model.addAttribute("storageDevicePage", pageNumber);
		model.addAttribute("totalStorageDevicePages", storageDevicePage.getTotalPages());
		model.addAttribute("totalStorageDeviceItems", storageDevicePage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("storageDevices", storageDevices);
		return "storage_devices";
	}
}