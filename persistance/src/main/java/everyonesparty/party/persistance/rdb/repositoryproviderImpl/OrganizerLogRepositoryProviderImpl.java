package everyonesparty.party.persistance.rdb.repositoryproviderImpl;

import everyonesparty.party.persistance.rdb.entity.OrganizerLogEntity;
import everyonesparty.party.persistance.rdb.repository.OrganizerLogRepository;
import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import everyonesparty.party.usecase.repositoryprovider.OrganizerLogRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class OrganizerLogRepositoryProviderImpl implements OrganizerLogRepositoryProvider {

    private final OrganizerLogRepository organizerLogRepository;

    @Override
    public List<OrganizerLog> findByOrganizerId(String organizerId) {
        return organizerLogRepository.findByOrganizerId(organizerId).stream()
                .map(OrganizerLogEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizerLog> findByOrganizerIdPartyStatus(String organizerId, PartyStatus partyStatus) {
        return organizerLogRepository.findByOrganizerIdAndPartyStatus(organizerId, partyStatus).stream()
                .map(OrganizerLogEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizerLog> findByOrganizerIdPartyStatusIn(String organizerId, Collection<PartyStatus> partyStatus) {
        return organizerLogRepository.findByOrganizerIdAndPartyStatusIn(organizerId, partyStatus).stream()
                .map(OrganizerLogEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrganizerLog> save(OrganizerLog organizerLog) {
        return Optional.ofNullable(organizerLogRepository.save(OrganizerLogEntity.fromDomain(organizerLog)))
                .map(OrganizerLogEntity::toDomain);
    }
}
