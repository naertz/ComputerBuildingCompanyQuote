package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import computerbuilding.beans.Storage;

import computerbuilding.repository.StorageRepository;


public class StorageService implements StorageServiceInterface{
	@Autowired
	private StorageRepository StorageRepository;

	@Override
	public List<Storage> getAllStorage() {
		return StorageRepository.findAll();
	}

	@Override
	public void updateStorage(Storage storage) {
		this.StorageRepository.save(storage);
	}

	@Override
	public Storage getStorageById(long id) {
		Optional<Storage> optional = StorageRepository.findById(id);
		Storage storage = null;
		if (optional.isPresent()) {
			storage = optional.get();
		} else {
			throw new RuntimeException("Storage not found for id: " + id);
		}
		return storage;
	}

	@Override
	public void deleteStorageById(long id) {
		this.StorageRepository.deleteById(id);
	}

	@Override
	public Page<Storage> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.StorageRepository.findAll(pageable);
	}
}