package everyonesparty.party.persistance.rdb.entity;

import everyonesparty.party.persistance.rdb.converter.DateConverter;
import everyonesparty.party.persistance.rdb.converter.PartyStatusConverter;
import everyonesparty.party.persistance.rdb.converter.MatchMethodConverter;
import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.domain.enums.codevalue.Date;
import everyonesparty.party.usecase.domain.enums.codevalue.MatchMethod;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.EAGER;

/***
 * > 파티장 로그
 */
@Entity
@Table(name = "organizer_log")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrganizerLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_log_id")
    private Long id;

    @NotNull
    private Integer memberCount;

    @NotNull
    @Convert(converter = MatchMethodConverter.class)
    private MatchMethod matchMethod;

    @NotNull
    @Convert(converter = DateConverter.class)
    private Date settlementDate;

    @NotNull
    private Long accountId; // 계좌번호를 직접 결재 서비스로 전달하면 안됨 -> 계좌 테이블의 주요키를 Long 타입의 임의 값으로 설정

    @NotNull
    @Enumerated(EnumType.STRING)
    private OttName ottName;

    @NotNull
    private String ottId;

    @NotNull
    private String ottPassword; // TODO: 암호화 처리

    @NotNull
    @Convert(converter = PartyStatusConverter.class)
    private PartyStatus partyStatus;

    //TODO: 유니크 값으로 검증 설정 & 카카오에서 주는 string len 설정
    @NotNull
    private String organizerId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // 파티가 사라지면 멤버도 전파
    @JoinColumn(name="fk_organizer_log_member_log")
    private List<MemberLogEntity> memberLogEntities;

    /***
     * > 파티장에 파티원 추가. 
     * > 즉, 매칭 성공 시 이 메소드를 통해 디비에 메칭 성공 사실을 기록
     * @param memberLogEntity
     */
    public void addMemberLog(MemberLogEntity memberLogEntity) {
        memberLogEntity.addOrganizerLogEntity(this);    // 관계에 주인도 메핑 필요

        if(memberLogEntities == null) {
            memberLogEntities = new ArrayList<>();
        }
        memberLogEntities.add(memberLogEntity);
    }

    /***
     * > OrganizerLogEntity 인스턴스로부터 새로운 OrganizerLog 도메인 객체 생성
     * @return
     */
    public OrganizerLog toDomain() {
        return OrganizerLog.builder()
                .id(this.id)
                .memberCount(this.memberCount)
                .matchMethod(this.matchMethod)
                .settlementDate(this.settlementDate)
                .accountId(this.accountId)
                .ottName(this.ottName)
                .ottId(this.ottId)
                .ottPassword(this.ottPassword)
                .partyStatus(this.partyStatus)
                .organizerId(this.organizerId)
                .memberLogs(convertMemberLogEntitiesToDomain(this.memberLogEntities))
                // fixme: cascade.lazy 설정이 의미없어지는 지점 -> 이럴땐 보통 어떻게 도메인 & 엔티티 메핑 하는지??
                // fixme: 이 경우는 파티장 하위의 파티원이 많지 않기 때문에 바로 가져오도록 하기
                // fixme: 하위 레코드가 많을 경우 연관관계를 만들지말고 각자 가져오는것으로 해결?
                .build();
    }

    // TODO: DTO 마다 있는 fromDomian 을 공통화 할 수 없을까?
    /***
     * > organizerLog 도메인 객체를 파라미터로 받아서 OrganizerLogEntity 생성
     * @param organizerLog
     * @return
     */
    public static OrganizerLogEntity fromDomain(OrganizerLog organizerLog) {
        return OrganizerLogEntity.builder()
                .id(organizerLog.getId())
                .memberCount(organizerLog.getMemberCount())
                .matchMethod(organizerLog.getMatchMethod())
                .settlementDate(organizerLog.getSettlementDate())
                .accountId(organizerLog.getAccountId())
                .ottName(organizerLog.getOttName())
                .ottId(organizerLog.getOttId())
                .ottPassword(organizerLog.getOttPassword())
                .partyStatus(organizerLog.getPartyStatus())
                .organizerId(organizerLog.getOrganizerId())
                .memberLogEntities(convertMemberLogsToEntity(organizerLog.getMemberLogs()))
                .build();
    }

    private List<MemberLog> convertMemberLogEntitiesToDomain (List<MemberLogEntity> memberLogEntities) {
        return CollectionUtils.emptyIfNull(memberLogEntities).stream()
                .map(MemberLogEntity::toDomain)
                .collect(Collectors.toList());
    }

    private static List<MemberLogEntity> convertMemberLogsToEntity(List<MemberLog> memberLogs) {
        return CollectionUtils.emptyIfNull(memberLogs).stream()
                .map(MemberLogEntity::fromDomain)
                .collect(Collectors.toList());
    }
}
