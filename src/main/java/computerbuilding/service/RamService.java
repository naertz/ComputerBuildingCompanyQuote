package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import computerbuilding.beans.Ram;
import computerbuilding.repository.RamRepository;

@Service
public class RamService implements RamServiceInterface{
	@Autowired
	private RamRepository ramRepository;

	@Override
	public List<Ram> getAllRam() {
		return ramRepository.findAll();
	}

	@Override
	public void updateRam(Ram ram) {
		this.ramRepository.save(ram);
	}

	@Override
	public Ram getRamById(long id) {
		Optional<Ram> optional = ramRepository.findById(id);
		Ram ram = null;
		if (optional.isPresent()) {
			ram = optional.get();
		} else {
			throw new RuntimeException("Ram not found for id: " + id);
		}
		return ram;
	}

	@Override
	public void deleteRamById(long id) {
		this.ramRepository.deleteById(id);
	}

	@Override
	public Page<Ram> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.ramRepository.findAll(pageable);
	}
}
