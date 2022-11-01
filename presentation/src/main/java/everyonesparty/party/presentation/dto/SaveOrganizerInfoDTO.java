package everyonesparty.party.presentation.dto;

import everyonesparty.party.usecase.domain.CurrentOttStatus;
import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.domain.enums.codevalue.Date;
import everyonesparty.party.usecase.domain.enums.codevalue.MatchMethod;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/***
 * > 단일 파티정보 저장 관련 DTO
 */
public class SaveOrganizerInfoDTO {


    /***
     * > 단일 파티정보 저장 요청에는 {id, memberLogs} 정보가 빠진다
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Req {

        @NotNull
        private Integer memberCount;

        @NotNull
        private MatchMethod matchMethod;

        @NotNull
        private Date settlementDate;

        @NotNull
        private Long accountId; // 계좌번호를 직접 결재 서비스로 전달하면 안됨 -> 계좌 테이블의 주요키를 Long 타입의 임의 값으로 설정

        @NotNull
        private OttName ottName;
        
        @NotBlank
        private String ottId;
        
        @NotBlank
        private String ottPassword; // TODO: 암호화 처리

        @NotNull
        private PartyStatus partyStatus;
        
        @NotBlank
        private String organizerId;

        public OrganizerLog toDomain() {
            return OrganizerLog.builder()
                    .memberCount(this.memberCount)
                    .matchMethod(this.matchMethod)
                    .settlementDate(this.settlementDate)
                    .accountId(this.accountId)
                    .ottName(this.ottName)
                    .ottId(this.ottId)
                    .ottPassword(this.ottPassword)
                    .partyStatus(this.partyStatus)
                    .organizerId(this.organizerId)
                    .build();
        }
    }

    /***
     * > 단일 파티정보 응답에는 {memberLogs} 정보가 빠진다
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Res {

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

        // TODO: DTO 마다 있는 fromDomian 을 공통화 할 수 없을까?
        public static Res fromDomian(OrganizerLog organizerLog) {
            return Res.builder()
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
                    .build();
        }

    }
}
