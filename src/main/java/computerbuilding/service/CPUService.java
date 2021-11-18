/**
 *@author Jake Soulinthavong - jakesoul
 *CIS175 - Fall 2021
 *September 23, 2021
 */

package computerbuilding.service;

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
	private CPURepository cPURepository;

	@Override
	public List<CPU> getAllCPUs() {
		return cPURepository.findAll();
	}

	@Override
	public void updateCPU(CPU cpu) {
		this.cPURepository.save(cpu);
	}

	@Override
	public CPU getCPUById(long id) {
		Optional<CPU> optional = cPURepository.findById(id);
		CPU cPU = null;
		if (optional.isPresent()) {
			cPU = optional.get();
		} else {
			throw new RuntimeException("CPU not found for id: " + id);
		}
		return cPU;
	}

	@Override
	public void deleteCPUById(long id) {
		this.cPURepository.deleteById(id);
	}

	@Override
	public Page<CPU> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.cPURepository.findAll(pageable);
	}
}
