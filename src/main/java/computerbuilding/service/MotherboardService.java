/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
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

import computerbuilding.beans.Motherboard;
import computerbuilding.repository.MotherboardRepository;

@Service
public class MotherboardService implements MotherboardServiceInterface {
	@Autowired
	private MotherboardRepository motherboardRepository;

	@Override
	public List<Motherboard> getAllMotherboards() {
		return motherboardRepository.findAll();
	}

	@Override
	public void updateMotherboard(final Motherboard motherboard) {
		motherboardRepository.save(motherboard);
	}

	@Override
	public Motherboard getMotherboardById(final long id) {
		final Optional<Motherboard> optional = motherboardRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Motherboard not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteMotherboardById(final long id) {
		motherboardRepository.deleteById(id);
	}

	@Override
	public Page<Motherboard> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return motherboardRepository.findAll(pageable);
	}
}
