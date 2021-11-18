package computerbuilding.service;

/**
 *@author Jake Soulinthavong - jakesoul
 *CIS175 - Fall 2021
 *September 23, 2021
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import computerbuilding.beans.CPU;
import computerbuilding.repository.CPURepository;

@Service
public class CPUService implements CPUServiceInterface {
	@Autowired
	private CPURepository cpuRepository;

	@Override
	public List<CPU> getAllCPUs() {
		return cpuRepository.findAll();
	}

	@Override
	public void updateCPU(CPU cpu) {
		this.cpuRepository.save(cpu);
	}

	@Override
	public CPU getCPUById(long id) {
		Optional<CPU> optional = cpuRepository.findById(id);
		CPU cpu = null;
		if (optional.isPresent()) {
			cpu = optional.get();
		} else {
			throw new RuntimeException("CPU not found for id: " + id);
		}
		return cpu;
	}

	@Override
	public void deleteCPUById(long id) {
		this.cpuRepository.deleteById(id);
	}

	@Override
	public Page<CPU> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.cpuRepository.findAll(pageable);
	}
}
