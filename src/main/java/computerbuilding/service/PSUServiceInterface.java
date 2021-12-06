/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 5, 2021
 */
package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.PSU;

public interface PSUServiceInterface {
	List<PSU> getAllPSUs();

	void updatePSU(PSU pSU);

	PSU getPSUById(long id);

	void deletePSUById(long id);

	Page<PSU> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
