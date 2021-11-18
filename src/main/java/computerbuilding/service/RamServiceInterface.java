package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.Ram;

public interface RamServiceInterface {
	List<Ram> getAllRam();
	void updateRam(Ram ram);
	Ram getRamById(long id);
	void deleteRamById(long id);
	Page<Ram> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}

