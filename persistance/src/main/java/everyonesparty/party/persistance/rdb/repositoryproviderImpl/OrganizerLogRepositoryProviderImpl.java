package everyonesparty.party.persistance.rdb.repositoryproviderImpl;

import everyonesparty.party.persistance.rdb.entity.OrganizerLogEntity;
import everyonesparty.party.persistance.rdb.repository.OrganizerLogRepository;
import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.repositoryprovider.OrganizerLogRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class OrganizerLogRepositoryProviderImpl implements OrganizerLogRepositoryProvider {

    private final OrganizerLogRepository organizerLogRepository;

    @Override
    public Optional<OrganizerLog> findByOrganizerId(String organizerId) {
        return organizerLogRepository.findByOrganizerId(organizerId)
                .map(OrganizerLogEntity::toDomain);
    }

    @Override
    public Optional<OrganizerLog> save(OrganizerLog organizerLog) {
        return Optional.ofNullable(organizerLogRepository.save(OrganizerLogEntity.fromDomain(organizerLog)))
                .map(OrganizerLogEntity::toDomain);
    }
}
