package everyonesparty.party.persistance.rdb.repository;

import everyonesparty.party.persistance.rdb.entity.OrganizerLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerLogRepository extends JpaRepository<OrganizerLogEntity, Long> {
    Optional<OrganizerLogEntity> findByOrganizerId(String organizerId);

}
