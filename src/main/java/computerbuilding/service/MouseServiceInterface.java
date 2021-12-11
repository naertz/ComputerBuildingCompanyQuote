/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 10, 2021
 */
package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.Mouse;

public interface MouseServiceInterface {
	List<Mouse> getAllMice();

	void updateMouse(Mouse mouse);

	Mouse getMouseById(long id);

	void deleteMouseById(long id);

	Page<Mouse> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
