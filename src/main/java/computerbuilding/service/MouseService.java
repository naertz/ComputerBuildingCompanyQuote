/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 10, 2021
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

import computerbuilding.beans.Mouse;
import computerbuilding.repository.MouseRepository;

@Service
public class MouseService implements MouseServiceInterface {
	@Autowired
	private MouseRepository mouseRepository;

	@Override
	public List<Mouse> getAllMice() {
		return mouseRepository.findAll();
	}

	@Override
	public void updateMouse(final Mouse mouse) {
		mouseRepository.save(mouse);
	}

	@Override
	public Mouse getMouseById(final long id) {
		final Optional<Mouse> optional = mouseRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Mouse not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteMouseById(final long id) {
		mouseRepository.deleteById(id);
	}

	@Override
	public Page<Mouse> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return mouseRepository.findAll(pageable);
	}
}
