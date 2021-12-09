package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.SoundCard;

public interface SoundCardServiceInterface {
	List<SoundCard> getAllSoundCards();

	void updateSoundCard(SoundCard soundCard);

	SoundCard getSoundCardById(long id);

	void deleteSoundCardById(long id);

	Page<SoundCard> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
