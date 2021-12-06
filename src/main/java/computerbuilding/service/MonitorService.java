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

import computerbuilding.beans.Monitor;
import computerbuilding.repository.MonitorRepository;

@Service
public class MonitorService implements MonitorServiceInterface {
	@Autowired
	private MonitorRepository monitorRepository;

	@Override
	public List<Monitor> getAllMonitors() {
		return monitorRepository.findAll();
	}

	@Override
	public void updateMonitor(final Monitor monitor) {
		monitorRepository.save(monitor);
	}

	@Override
	public Monitor getMonitorById(final long id) {
		final Optional<Monitor> optional = monitorRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Monitor not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteMonitorById(final long id) {
		monitorRepository.deleteById(id);
	}

	@Override
	public Page<Monitor> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return monitorRepository.findAll(pageable);
	}
}