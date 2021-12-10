package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.CPUCooler;

public interface CPUCoolerServiceInterface {
	List<CPUCooler> getAllCPUCoolers();

	void updateCPUCooler(CPUCooler cPUCooler);

	CPUCooler getCPUCoolerById(long id);

	void deleteCPUCoolerById(long id);

	Page<CPUCooler> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
