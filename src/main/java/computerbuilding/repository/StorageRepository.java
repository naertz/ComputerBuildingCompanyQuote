package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> { }
