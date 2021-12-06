/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
 */
package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.Motherboard;

public interface MotherboardServiceInterface {
	List<Motherboard> getAllMotherboards();

	void updateMotherboard(Motherboard motherboard);

	Motherboard getMotherboardById(long id);

	void deleteMotherboardById(long id);

	Page<Motherboard> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
