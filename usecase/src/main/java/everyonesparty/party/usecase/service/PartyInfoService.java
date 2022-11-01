package everyonesparty.party.usecase.service;

import everyonesparty.party.usecase.domain.CurrentUserPartyInfo;
import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.repositoryprovider.MemberLogRepositoryProvider;
import everyonesparty.party.usecase.repositoryprovider.OrganizerLogRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyInfoService {

    private final OrganizerLogRepositoryProvider organizerLogRepositoryProvider;
    private final MemberLogRepositoryProvider memberLogRepositoryProvider;

    /***
     * > kakaoId 에 해당하는 사용자의 파티정보 조회
     * @param kakaoId
     */
    public CurrentUserPartyInfo findByKakaoId(String kakaoId) {
        return CurrentUserPartyInfo.builder()
                .kakaoId(kakaoId)
                .memberInfo(memberLogRepositoryProvider.findByMemberId(kakaoId))
                .organizerInfo(organizerLogRepositoryProvider.findByOrganizerId(kakaoId))
                .build();
    }

    public OrganizerLog saveOrganizerInfo(OrganizerLog reqOrganizerLog) {
        return organizerLogRepositoryProvider.save(reqOrganizerLog)
                .orElseThrow(() -> new RuntimeException()); // TODO: save 실패 시 로깅이나 유연하고 명시적인 예외처리 가능하도록
    }
}
