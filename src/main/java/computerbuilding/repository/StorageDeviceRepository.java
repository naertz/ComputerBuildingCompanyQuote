package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.StorageDevice;

@Repository
public interface StorageDeviceRepository extends JpaRepository<StorageDevice, Long> { }
