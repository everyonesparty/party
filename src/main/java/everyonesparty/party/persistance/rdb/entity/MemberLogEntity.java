package everyonesparty.party.persistance.rdb.entity;

import everyonesparty.party.persistance.rdb.converter.OttNameConverter;
import everyonesparty.party.persistance.rdb.converter.PartyStatusConverter;
import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.EAGER;
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
    @NotNull
    @Column(name = "member_log_id")
    private Long id;

    @NotNull
    @Convert(converter = OttNameConverter.class)
    private OttName ottName;

    @NotNull
    @Convert(converter = PartyStatusConverter.class)
    private PartyStatus partyStatus;

    @NotNull
    private String memberId;

    @NotNull
    private Long cardId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "fk_organizer_log_member_log")   // fk 명명 규칙 => fk_${부모}_${자식}
    private OrganizerLogEntity organizerLogEntity;

    public void addOrganizerLogEntity(OrganizerLogEntity organizerLogEntity) {
        this.organizerLogEntity = organizerLogEntity;
    }

    public MemberLog toDomain() {
        return MemberLog.builder()
                .id(this.id)
                .ottName(this.ottName)
                .partyStatus(this.partyStatus)
                .memberId(this.memberId)
                .cardId(this.cardId)
                .organizerLog(this.organizerLogEntity.toDomain())
                .build();
    }

    public static MemberLogEntity fromDomain(MemberLog memberLog) {
        return MemberLogEntity.builder()
                .id(memberLog.getId())
                .ottName(memberLog.getOttName())
                .partyStatus(memberLog.getPartyStatus())
                .memberId(memberLog.getMemberId())
                .cardId(memberLog.getCardId())
                .organizerLogEntity(OrganizerLogEntity.fromDomain(memberLog.getOrganizerLog()))
                .build();
    }

}
