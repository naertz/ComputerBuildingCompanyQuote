package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import computerbuilding.beans.CPUCooler;
import computerbuilding.repository.CPUCoolerRepository;

@Service
public class CPUCoolerService implements CPUCoolerServiceInterface {
	@Autowired
	private CPUCoolerRepository cPUCoolerRepository;

	@Override
	public List<CPUCooler> getAllCPUCoolers() {
		return cPUCoolerRepository.findAll();
	}

	@Override
	public void updateCPUCooler(final CPUCooler cPUCooler) {
		cPUCoolerRepository.save(cPUCooler);
	}

	@Override
	public CPUCooler getCPUCoolerById(final long id) {
		final Optional<CPUCooler> optional = cPUCoolerRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("CPU cooler not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteCPUCoolerById(final long id) {
		cPUCoolerRepository.deleteById(id);
	}

	@Override
	public Page<CPUCooler> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return cPUCoolerRepository.findAll(pageable);
	}
}
