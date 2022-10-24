package everyonesparty.party.persistance.rdb.repositoryproviderImpl;

import everyonesparty.party.persistance.rdb.entity.MemberLogEntity;
import everyonesparty.party.persistance.rdb.repository.MemberLogRepository;
import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.repositoryprovider.MemberLogRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MemberLogRepositoryProviderImpl implements MemberLogRepositoryProvider {

    private final MemberLogRepository memberLogRepository;

    @Override
    public Optional<MemberLog> findByMemberId(String memberId) {
        return memberLogRepository.findByMemberId(memberId)
                .map(MemberLogEntity::toDomain);
    }

    @Override
    public Optional<MemberLog> save(MemberLog memberLog) {
        return Optional.ofNullable(memberLogRepository.save(MemberLogEntity.fromDomain(memberLog)))
                .map(MemberLogEntity::toDomain);
    }
}
