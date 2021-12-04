package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.ThermalCompound;

@Repository
public interface ThermalCompoundRepository extends JpaRepository<ThermalCompound, Long>{}
