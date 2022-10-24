package everyonesparty.party.usecase.repositoryprovider;

import everyonesparty.party.usecase.domain.MemberLog;

import java.util.Optional;

public interface MemberLogRepositoryProvider {
    Optional<MemberLog> findByMemberId(String memberId);

    Optional<MemberLog> save(MemberLog memberLog);
}
