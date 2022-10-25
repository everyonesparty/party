package everyonesparty.party.usecase.service;

import everyonesparty.party.usecase.domain.CurrentUserPartyInfo;
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
}
