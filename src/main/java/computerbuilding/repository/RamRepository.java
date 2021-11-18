package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.Ram;

@Repository
public interface RamRepository extends JpaRepository<Ram, Long> { }
