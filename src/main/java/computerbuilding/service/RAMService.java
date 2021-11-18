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
	public void updateRAM(RAM rAM) {
		this.rAMRepository.save(rAM);
	}

	@Override
	public RAM getRAMById(long id) {
		Optional<RAM> optional = rAMRepository.findById(id);
		RAM rAM = null;
		if (optional.isPresent()) {
			rAM = optional.get();
		} else {
			throw new RuntimeException("RAM not found for id: " + id);
		}
		return rAM;
	}

	@Override
	public void deleteRAMById(long id) {
		this.rAMRepository.deleteById(id);
	}

	@Override
	public Page<RAM> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.rAMRepository.findAll(pageable);
	}
}
