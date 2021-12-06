/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 6, 2021
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

import computerbuilding.beans.Keyboard;
import computerbuilding.repository.KeyboardRepository;

@Service
public class KeyboardService implements KeyboardServiceInterface {
	@Autowired
	private KeyboardRepository keyboardRepository;

	@Override
	public List<Keyboard> getAllKeyboards() {
		return keyboardRepository.findAll();
	}

	@Override
	public void updateKeyboard(final Keyboard keyboard) {
		keyboardRepository.save(keyboard);
	}

	@Override
	public Keyboard getKeyboardById(final long id) {
		final Optional<Keyboard> optional = keyboardRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Keyboard not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteKeyboardById(final long id) {
		keyboardRepository.deleteById(id);
	}

	@Override
	public Page<Keyboard> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return keyboardRepository.findAll(pageable);
	}
}