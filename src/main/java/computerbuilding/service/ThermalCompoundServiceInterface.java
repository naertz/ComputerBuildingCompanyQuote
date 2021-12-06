package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.ThermalCompound;

public interface ThermalCompoundServiceInterface {
	List<ThermalCompound> getAllThermalCompounds();

	void updateThermalCompound(ThermalCompound thermalCompound);

	ThermalCompound getThermalCompoundById(long id);

	void deleteThermalCompoundById(long id);

	Page<ThermalCompound> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
