package everyonesparty.party.persistance.rdb.repository;

import everyonesparty.party.persistance.rdb.entity.MemberLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberLogRepository extends JpaRepository<MemberLogEntity, Long> {
    Optional<MemberLogEntity> findByMemberId(String memberId);
}
