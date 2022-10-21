package everyonesparty.party.usecase.domain;

import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/***
 * > 최대한 immutable 하게 보호
 */
@Getter
@AllArgsConstructor
@Builder
public class MemberLog {

    private Long id;

    private OttName ottName;

    private PartyStatus partyStatus;

    private String memberId;

    private Long cardId;

    private OrganizerLog organizerLog;
}
