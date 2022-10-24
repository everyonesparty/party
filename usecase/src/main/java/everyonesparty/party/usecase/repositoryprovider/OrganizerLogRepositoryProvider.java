package everyonesparty.party.usecase.repositoryprovider;

import everyonesparty.party.usecase.domain.OrganizerLog;

import java.util.List;
import java.util.Optional;

public interface OrganizerLogRepositoryProvider {
    List<OrganizerLog> findByOrganizerId(String organizerId);

    Optional<OrganizerLog> save(OrganizerLog organizerLog);

}
