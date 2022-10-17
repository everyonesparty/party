package everyonesparty.party.persistance.rdb.entity;

import everyonesparty.party.persistance.rdb.converter.DateConverter;
import everyonesparty.party.persistance.rdb.converter.MatchMethodConverter;
import everyonesparty.party.persistance.rdb.converter.OttNameConverter;
import everyonesparty.party.persistance.rdb.converter.PartyStatusConverter;
import everyonesparty.party.usecase.domain.enums.Date;
import everyonesparty.party.usecase.domain.enums.MatchMethod;
import everyonesparty.party.usecase.domain.enums.OttName;
import everyonesparty.party.usecase.domain.enums.PartyStatus;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

/***
 * > 파티장 로그
 */
@Entity
@Table(name = "organizer_log_table")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrganizerLogEntity {

    @Id
    @Column(name = "organizer_log_id")
    private Long id;

    private Integer memberCount;

    @Convert(converter = MatchMethodConverter.class)
    private MatchMethod matchMethod;

    @Convert(converter = DateConverter.class)
    private Date settlementDate;

    private Long accountId; // 계좌번호를 직접 결재 서비스로 전달하면 안됨 -> 계좌 테이블의 주요키를 Long 타입의 임의 값으로 설정

    @Convert(converter = OttNameConverter.class)
    private OttName ottName;

    private String ottId;

    private String ottPassword; // TODO: 암호화 처리

    @Convert(converter = PartyStatusConverter.class)
    private PartyStatus partyStatus;

    private String organizerId;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL) // 파티가 사라지면 멤버도 전파
    @JoinColumn(name="organizer_log_id")
    private List<MemberLogEntity> memberLogs;
}
