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

import computerbuilding.beans.GPU;
import computerbuilding.repository.GPURepository;

@Service
public class GPUService implements GPUServiceInterface {
	@Autowired
	private GPURepository gpuRepository;

	@Override
	public List<GPU> getAllGPUs() {
		return gpuRepository.findAll();
	}

	@Override
	public void updateGPU(GPU gpu) {
		this.gpuRepository.save(gpu);
	}

	@Override
	public GPU getGPUById(long id) {
		Optional<GPU> optional = gpuRepository.findById(id);
		GPU gpu = null;
		if (optional.isPresent()) {
			gpu = optional.get();
		} else {
			throw new RuntimeException("GPU not found for id: " + id);
		}
		return gpu;
	}

	@Override
	public void deleteGPUById(long id) {
		this.gpuRepository.deleteById(id);
	}

	@Override
	public Page<GPU> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.gpuRepository.findAll(pageable);
	}
}
