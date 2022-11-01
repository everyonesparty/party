package everyonesparty.party.persistance.rdb.repositoryproviderImpl;

import everyonesparty.party.persistance.rdb.entity.MemberLogEntity;
import everyonesparty.party.persistance.rdb.repository.MemberLogRepository;
import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import everyonesparty.party.usecase.repositoryprovider.MemberLogRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
    public List<MemberLog> findByMemberIdAndPartyStatus(String memberId, PartyStatus partyStatus) {
        return memberLogRepository.findByMemberIdAndPartyStatus(memberId, partyStatus).stream()
                .map(MemberLogEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberLog> findByMemberIdAndPartyStatusIn(String memberId, Collection<PartyStatus> partyStatus) {
        return memberLogRepository.findByMemberIdAndPartyStatusIn(memberId, partyStatus).stream()
                .map(MemberLogEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MemberLog> save(MemberLog memberLog) {
        return Optional.ofNullable(memberLogRepository.save(MemberLogEntity.fromDomain(memberLog)))
                .map(MemberLogEntity::toDomain);
    }
}
