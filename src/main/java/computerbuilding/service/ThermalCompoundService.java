package computerbuilding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import computerbuilding.beans.ThermalCompound;
import computerbuilding.repository.ThermalCompoundRepository;

@Service
public class ThermalCompoundService implements ThermalCompoundServiceInterface {
	@Autowired
	private ThermalCompoundRepository thermalCompoundRepository;

	@Override
	public List<ThermalCompound> getAllThermalCompounds() {
		return thermalCompoundRepository.findAll();
	}

	@Override
	public void updateThermalCompound(final ThermalCompound thermalCompound) {
		thermalCompoundRepository.save(thermalCompound);
	}

	@Override
	public ThermalCompound getThermalCompoundById(final long id) {
		final Optional<ThermalCompound> optional = thermalCompoundRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Thermal compound not found for id: " + id);
		return optional.get();
	}

	@Override
	public void deleteThermalCompoundById(final long id) {
		thermalCompoundRepository.deleteById(id);
	}

	@Override
	public Page<ThermalCompound> findPaginated(final int pageNumber, final int pageSize, final String sortField, final String sortDirection) {
		final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return thermalCompoundRepository.findAll(pageable);
	}
}
