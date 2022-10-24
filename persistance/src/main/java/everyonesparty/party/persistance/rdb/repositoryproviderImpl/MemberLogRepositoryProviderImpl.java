package everyonesparty.party.persistance.rdb.repositoryproviderImpl;

import everyonesparty.party.persistance.rdb.entity.MemberLogEntity;
import everyonesparty.party.persistance.rdb.repository.MemberLogRepository;
import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.repositoryprovider.MemberLogRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class MemberLogRepositoryProviderImpl implements MemberLogRepositoryProvider {

    private final MemberLogRepository memberLogRepository;

    @Override
    public List<MemberLog> findByMemberId(String memberId) {
        return memberLogRepository.findByMemberId(memberId).stream()
                .map(MemberLogEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MemberLog> save(MemberLog memberLog) {
        return Optional.ofNullable(memberLogRepository.save(MemberLogEntity.fromDomain(memberLog)))
                .map(MemberLogEntity::toDomain);
    }
}
