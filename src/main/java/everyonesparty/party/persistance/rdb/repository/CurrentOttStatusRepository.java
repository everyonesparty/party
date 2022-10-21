package everyonesparty.party.persistance.rdb.repository;

import everyonesparty.party.persistance.rdb.entity.CurrentOttStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrentOttStatusRepository extends JpaRepository<CurrentOttStatusEntity, Long> {

    Optional<CurrentOttStatusEntity> findByOttName(String ottName);
}
