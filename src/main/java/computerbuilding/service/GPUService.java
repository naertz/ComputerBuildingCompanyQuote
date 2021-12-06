/**
 * @author Jake Soulinthavong - jakesoul
 * CIS175 - Fall 2021
 * September 23, 2021
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
	public void updateGPU(final GPU gpu) {
		gpuRepository.save(gpu);
	}

	@Override
	public GPU getGPUById(final long id) {
		final Optional<GPU> optional = gpuRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("GPU not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteGPUById(final long id) {
		gpuRepository.deleteById(id);
	}

	@Override
	public Page<GPU> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return gpuRepository.findAll(pageable);
	}
}