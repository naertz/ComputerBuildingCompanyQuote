/**
 * @author Jake Soulinthavong - jakesoul
 * CIS175 - Fall 2021
 * September 23, 2021
 */
package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.GPU;

public interface GPUServiceInterface {
	List<GPU> getAllGPUs();

	void updateGPU(GPU gPU);

	GPU getGPUById(long id);

	void deleteGPUById(long id);

	Page<GPU> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
