package everyonesparty.party.presentation.dto;

import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.domain.enums.codevalue.Date;
import everyonesparty.party.usecase.domain.enums.codevalue.MatchMethod;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class SavePartyInfoDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Req {
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

        public OrganizerLog toDomain() {
            return OrganizerLog.builder()
                    .memberCount(this.memberCount)
                    .matchMethod(this.matchMethod)
                    .settlementDate(this.settlementDate)
                    .accountId(this.accountId)
                    .ottName(this.ottName)
                    .build();
        }
    }
}
