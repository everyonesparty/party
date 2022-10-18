package everyonesparty.party.persistance.rdb.entity;

import everyonesparty.party.persistance.rdb.converter.OttNameConverter;
import everyonesparty.party.persistance.rdb.converter.PartyStatusConverter;
import everyonesparty.party.usecase.domain.enums.OttName;
import everyonesparty.party.usecase.domain.enums.PartyStatus;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/***
 * > 파티원 로그
 */
@Entity
@Table(name = "member_log")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberLogEntity {

    @Id
    @Column(name = "member_log_id")
    private Long id;

    @Convert(converter = OttNameConverter.class)
    private OttName ottName;

    @Convert(converter = PartyStatusConverter.class)
    private PartyStatus partyStatus;

    private String memberId;

    private Long cardId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_organizer_log_member_log")   // fk 명명 규칙 => fk_${부모}_${자식}
    private OrganizerLogEntity organizerLogs;
}
