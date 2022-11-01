package everyonesparty.party.usecase.repositoryprovider;

import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrganizerLogRepositoryProvider {
    List<OrganizerLog> findByOrganizerId(String organizerId);

    List<OrganizerLog> findByOrganizerIdAndPartyStatus(String organizerId, PartyStatus partyStatus);

    List<OrganizerLog> findByOrganizerIdAndPartyStatusIn(String organizerId, Collection<PartyStatus> partyStatus);

    List<OrganizerLog> findByOrganizerIdAndOttNameAndPartyStatusIn(String organizerId, OttName ottName, Collection<PartyStatus> partyStatus);

    List<OrganizerLog> findByOrganizerIdInAndPartyStatusIn(Collection<String> organizerId, Collection<PartyStatus> partyStatus);

    Optional<OrganizerLog> save(OrganizerLog organizerLog);

}
