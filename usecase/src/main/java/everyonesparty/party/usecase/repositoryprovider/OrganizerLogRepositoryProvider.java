package everyonesparty.party.usecase.repositoryprovider;

import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrganizerLogRepositoryProvider {
    List<OrganizerLog> findByOrganizerId(String organizerId);

    List<OrganizerLog> findByOrganizerIdPartyStatus(String organizerId, PartyStatus partyStatus);

    List<OrganizerLog> findByOrganizerIdPartyStatusIn(String organizerId, Collection<PartyStatus> partyStatus);

    Optional<OrganizerLog> save(OrganizerLog organizerLog);

}
