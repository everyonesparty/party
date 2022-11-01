package everyonesparty.party.persistance.rdb.repository;

import everyonesparty.party.persistance.rdb.entity.MemberLogEntity;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MemberLogRepository extends JpaRepository<MemberLogEntity, Long> {
    List<MemberLogEntity> findByMemberId(String memberId);

    List<MemberLogEntity> findByMemberIdAndPartyStatus(String memberId, PartyStatus partyStatus);

    List<MemberLogEntity> findByMemberIdAndPartyStatusIn(String memberId, Collection<PartyStatus> partyStatus);

    List<MemberLogEntity> findByMemberIdAndOttNameAndPartyStatusIn(String memberId, OttName ottName, Collection<PartyStatus> partyStatus);

    List<MemberLogEntity> findByMemberIdInAndPartyStatusIn(Collection<String> memberId, Collection<PartyStatus> partyStatus);
}
