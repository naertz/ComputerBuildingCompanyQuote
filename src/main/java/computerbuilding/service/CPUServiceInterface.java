package computerbuilding.service;

/**
 *@author Jake Soulinthavong - jakesoul
 *CIS175 - Fall 2021
 *September 23, 2021
 */
import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.CPU;

public interface CPUServiceInterface {
	List<CPU> getAllCPUs();
	void updateCPU(CPU cpu);
	CPU getCPUById(long id);
	void deleteCPUById(long id);
	Page<CPU> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
