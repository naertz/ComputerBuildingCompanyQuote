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
	public void updateThermalCompound(ThermalCompound thermalCompound) {
		this.thermalCompoundRepository.save(thermalCompound);
	}

	@Override
	public ThermalCompound getThermalCompoundById(long id) {
		Optional<ThermalCompound> optional = thermalCompoundRepository.findById(id);
		ThermalCompound thermalCompound = null;
		if (optional.isPresent()) {
			thermalCompound = optional.get();
		} else {
			throw new RuntimeException("Thermal compound not found for id: " + id);
		}
		return thermalCompound;
	}

	@Override
	public void deleteThermalCompoundById(long id) {
		this.thermalCompoundRepository.deleteById(id);
	}

	@Override
	public Page<ThermalCompound> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return this.thermalCompoundRepository.findAll(pageable);
	}
}
