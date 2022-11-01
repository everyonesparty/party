package everyonesparty.party.usecase.repositoryprovider;

import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberLogRepositoryProvider {
    List<MemberLog> findByMemberId(String memberId);

    List<MemberLog> findByMemberIdAndPartyStatus(String memberId, PartyStatus partyStatus);

    List<MemberLog> findByMemberIdAndPartyStatusIn(String memberId, Collection<PartyStatus> partyStatus);

    List<MemberLog> findByMemberIdAndOttNameAndPartyStatusIn(String memberId, OttName ottName, Collection<PartyStatus> partyStatus);

    List<MemberLog> findByMemberIdInAndPartyStatusIn(Collection<String> memberId, Collection<PartyStatus> partyStatus);

    Optional<MemberLog> save(MemberLog memberLog);
}
