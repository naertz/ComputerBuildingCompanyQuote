package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import computerbuilding.beans.SoundCard;
import computerbuilding.repository.SoundCardRepository;

@Service
public class SoundCardService implements SoundCardServiceInterface{
	@Autowired
	private SoundCardRepository soundCardRepository;

	@Override
	public List<SoundCard> getAllSoundCards() {
		return soundCardRepository.findAll();
	}

	@Override
	public void updateSoundCard(final SoundCard soundCard) {
		soundCardRepository.save(soundCard);
	}

	@Override
	public SoundCard getSoundCardById(final long id) {
		final Optional<SoundCard> optional = soundCardRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Sound card not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteSoundCardById(final long id) {
		soundCardRepository.deleteById(id);
	}

	@Override
	public Page<SoundCard> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return soundCardRepository.findAll(pageable);
	}
}
