package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.Storage;

public interface StorageServiceInterface {
	List<Storage> getAllStorage();
	void updateStorage(Storage storage);
	Storage getStorageById(long id);
	void deleteStorageById(long id);
	Page<Storage> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}