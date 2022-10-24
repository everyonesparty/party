package everyonesparty.party.persistance.rdb.repository;

import everyonesparty.party.persistance.rdb.entity.OrganizerLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizerLogRepository extends JpaRepository<OrganizerLogEntity, Long> {
    List<OrganizerLogEntity> findByOrganizerId(String organizerId);

}
