package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.RAM;

@Repository
public interface RAMRepository extends JpaRepository<RAM, Long> {}