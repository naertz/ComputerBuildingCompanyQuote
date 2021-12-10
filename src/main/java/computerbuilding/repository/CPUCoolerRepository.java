package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.CPUCooler;

@Repository
public interface CPUCoolerRepository extends JpaRepository<CPUCooler, Long> {}