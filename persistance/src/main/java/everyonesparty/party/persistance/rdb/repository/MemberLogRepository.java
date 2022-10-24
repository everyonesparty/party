package everyonesparty.party.persistance.rdb.repository;

import everyonesparty.party.persistance.rdb.entity.MemberLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLogRepository extends JpaRepository<MemberLogEntity, Long> {
    List<MemberLogEntity> findByMemberId(String memberId);
}
