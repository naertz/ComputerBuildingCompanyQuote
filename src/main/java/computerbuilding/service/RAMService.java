package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import computerbuilding.beans.RAM;
import computerbuilding.repository.RAMRepository;

@Service
public class RAMService implements RAMServiceInterface {
	@Autowired
	private RAMRepository rAMRepository;

	@Override
	public List<RAM> getAllRAM() {
		return rAMRepository.findAll();
	}

	@Override
	public void updateRAM(final RAM rAM) {
		rAMRepository.save(rAM);
	}

	@Override
	public RAM getRAMById(final long id) {
		final Optional<RAM> optional = rAMRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("RAM not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteRAMById(final long id) {
		rAMRepository.deleteById(id);
	}

	@Override
	public Page<RAM> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return rAMRepository.findAll(pageable);
	}
}