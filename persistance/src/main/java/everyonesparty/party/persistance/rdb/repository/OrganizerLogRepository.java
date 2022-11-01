package everyonesparty.party.persistance.rdb.repository;

import everyonesparty.party.persistance.rdb.entity.OrganizerLogEntity;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface OrganizerLogRepository extends JpaRepository<OrganizerLogEntity, Long> {
    List<OrganizerLogEntity> findByOrganizerId(String organizerId);

    List<OrganizerLogEntity> findByOrganizerIdAndPartyStatus(String organizerId, PartyStatus partyStatus);

    List<OrganizerLogEntity> findByOrganizerIdAndPartyStatusIn(String organizerId, Collection<PartyStatus> partyStatus);

    List<OrganizerLogEntity> findByOrganizerIdAndOttNameAndPartyStatusIn(String organizerId, OttName ottName, Collection<PartyStatus> partyStatus);

    List<OrganizerLogEntity> findByOrganizerIdInAndPartyStatusIn(Collection<String> organizerId, Collection<PartyStatus> partyStatus);
}

