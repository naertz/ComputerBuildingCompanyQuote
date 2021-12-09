package computerbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import computerbuilding.beans.SoundCard;

@Repository
public interface SoundCardRepository extends JpaRepository<SoundCard, Long> {}
