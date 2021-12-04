package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import computerbuilding.beans.StorageDevice;
import computerbuilding.repository.StorageDeviceRepository;

@Service
public class StorageDeviceService implements StorageDeviceServiceInterface {
	@Autowired
	private StorageDeviceRepository storageDeviceRepository;

	@Override
	public List<StorageDevice> getAllStorageDevices() {
		return storageDeviceRepository.findAll();
	}

	@Override
	public void updateStorageDevice(StorageDevice storageDevice) {
		this.storageDeviceRepository.save(storageDevice);
	}

	@Override
	public StorageDevice getStorageDeviceById(long id) {
		Optional<StorageDevice> optional = storageDeviceRepository.findById(id);
		StorageDevice storageDevice = null;
		if (optional.isPresent()) {
			storageDevice = optional.get();
		} else {
			throw new RuntimeException("Storage device not found for id: " + id);
		}
		return storageDevice;
	}

	@Override
	public void deleteStorageDeviceById(long id) {
		this.storageDeviceRepository.deleteById(id);
	}

	@Override
	public Page<StorageDevice> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.storageDeviceRepository.findAll(pageable);
	}
}