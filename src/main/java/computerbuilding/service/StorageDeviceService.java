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
	public void updateStorageDevice(final StorageDevice storageDevice) {
		storageDeviceRepository.save(storageDevice);
	}

	@Override
	public StorageDevice getStorageDeviceById(final long id) {
		final Optional<StorageDevice> optional = storageDeviceRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Storage device not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteStorageDeviceById(final long id) {
		storageDeviceRepository.deleteById(id);
	}

	@Override
	public Page<StorageDevice> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return storageDeviceRepository.findAll(pageable);
	}
}
