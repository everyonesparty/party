package everyonesparty.party.presentation.dto;


import everyonesparty.party.usecase.domain.CurrentUserPartyInfo;
import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.domain.OrganizerLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class CurrentUserPartyInfoDTO {
//    public static class Req {
//
//    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Res {
        String kakaoId;
        List<MemberLog> memberInfo;
        List<OrganizerLog> organizerInfo;


        // TODO: DTO 마다 있는 fromDomian 을 공통화 할 수 없을까?
        public static CurrentUserPartyInfoDTO.Res fromDomian(CurrentUserPartyInfo currentUserPartyInfo) {
            return Res.builder()
                    .kakaoId(currentUserPartyInfo.getKakaoId())
                    .memberInfo(currentUserPartyInfo.getMemberInfo())
                    .organizerInfo(currentUserPartyInfo.getOrganizerInfo())
                    .build();
        }
    }
}
