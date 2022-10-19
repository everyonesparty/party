package everyonesparty.party.usecase.domain;

import everyonesparty.party.usecase.domain.enums.codevalue.Date;
import everyonesparty.party.usecase.domain.enums.codevalue.MatchMethod;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/***
 * > 최대한 immutable 하게 보호
 */
@Getter
@AllArgsConstructor
@Builder
public class OrganizerLog {

    private Long id;

    private Integer memberCount;

    private MatchMethod matchMethod;

    private Date settlementDate;

    private Long accountId; // 계좌번호를 직접 결재 서비스로 전달하면 안됨 -> 계좌 테이블의 주요키를 Long 타입의 임의 값으로 설정

    private OttName ottName;

    private String ottId;

    private String ottPassword; // TODO: 암호화 처리

    private PartyStatus partyStatus;

    private String organizerId;

    private List<MemberLog> memberLogs;
}
