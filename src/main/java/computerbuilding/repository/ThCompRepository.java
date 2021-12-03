package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.ThComp;

@Repository
public interface ThCompRepository extends JpaRepository<ThComp, Long>{}
