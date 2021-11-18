package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.RAM;

public interface RAMServiceInterface {
	List<RAM> getAllRAM();
	void updateRAM(RAM rAM);
	RAM getRAMById(long id);
	void deleteRAMById(long id);
	Page<RAM> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}

