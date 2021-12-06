/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 6, 2021
 */
package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.Keyboard;

public interface KeyboardServiceInterface {
	List<Keyboard> getAllKeyboards();

	void updateKeyboard(Keyboard keyboard);

	Keyboard getKeyboardById(long id);

	void deleteKeyboardById(long id);

	Page<Keyboard> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
