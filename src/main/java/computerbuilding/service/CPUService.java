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
	public void updateCPU(final CPU cPU) {
		cPURepository.save(cPU);
	}

	@Override
	public CPU getCPUById(final long id) {
		final Optional<CPU> optional = cPURepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("CPU not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteCPUById(final long id) {
		cPURepository.deleteById(id);
	}

	@Override
	public Page<CPU> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return cPURepository.findAll(pageable);
	}
}