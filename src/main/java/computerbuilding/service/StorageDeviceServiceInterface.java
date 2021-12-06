package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.StorageDevice;

public interface StorageDeviceServiceInterface {
	List<StorageDevice> getAllStorageDevices();

	void updateStorageDevice(StorageDevice storageDevice);

	StorageDevice getStorageDeviceById(long id);

	void deleteStorageDeviceById(long id);

	Page<StorageDevice> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
