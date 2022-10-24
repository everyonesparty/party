package everyonesparty.party.usecase.repositoryprovider;

import everyonesparty.party.usecase.domain.MemberLog;

import java.util.List;
import java.util.Optional;

public interface MemberLogRepositoryProvider {
    List<MemberLog> findByMemberId(String memberId);

    Optional<MemberLog> save(MemberLog memberLog);
}
