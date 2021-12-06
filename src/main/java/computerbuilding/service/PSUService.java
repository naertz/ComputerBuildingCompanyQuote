/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 5, 2021
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

import computerbuilding.beans.PSU;
import computerbuilding.repository.PSURepository;

@Service
public class PSUService implements PSUServiceInterface {
	@Autowired
	private PSURepository pSURepository;

	@Override
	public List<PSU> getAllPSUs() {
		return pSURepository.findAll();
	}

	@Override
	public void updatePSU(final PSU pSU) {
		pSURepository.save(pSU);
	}

	@Override
	public PSU getPSUById(final long id) {
		final Optional<PSU> optional = pSURepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("PSU not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deletePSUById(final long id) {
		pSURepository.deleteById(id);
	}

	@Override
	public Page<PSU> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return pSURepository.findAll(pageable);
	}
}
