package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import computerbuilding.beans.ThComp;
import computerbuilding.repository.ThCompRepository;

public class ThCompService implements ThCompServiceInterface{
	@Autowired
	private ThCompRepository ThCompRepository;

	@Override
	public List<ThComp> getAllThComp() {
		return ThCompRepository.findAll();
	}

	@Override
	public void updateThComp(ThComp thcomp) {
		this.ThCompRepository.save(thcomp);
	}

	@Override
	public ThComp getThCompById(long id) {
		Optional<ThComp> optional = ThCompRepository.findById(id);
		ThComp thcomp = null;
		if (optional.isPresent()) {
			thcomp = optional.get();
		} else {
			throw new RuntimeException("ThComp not found for id: " + id);
		}
		return thcomp;
	}

	@Override
	public void deleteThCompById(long id) {
		this.ThCompRepository.deleteById(id);
	}

	@Override
	public Page<ThComp> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.ThCompRepository.findAll(pageable);
	}
}
