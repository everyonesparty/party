package everyonesparty.party.usecase.service;

import everyonesparty.party.usecase.domain.CurrentUserPartyInfo;
import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import everyonesparty.party.usecase.repositoryprovider.MemberLogRepositoryProvider;
import everyonesparty.party.usecase.repositoryprovider.OrganizerLogRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartyInfoService {

    private final OrganizerLogRepositoryProvider organizerLogRepositoryProvider;
    private final MemberLogRepositoryProvider memberLogRepositoryProvider;

    /***
     * > kakaoId 에 해당하는 사용자의 파티정보 조회
     * > 지금 매칭된 상태거나 진행중인 파티정보만 가져와야 함
     * 
     * @param kakaoId
     */
    public CurrentUserPartyInfo findMatchingPartyInfoByKakaoId(String kakaoId) {
        return CurrentUserPartyInfo.builder()
                .kakaoId(kakaoId)
                .memberInfo(memberLogRepositoryProvider.findByMemberIdAndPartyStatusIn(kakaoId, List.of(PartyStatus.MATCHING, PartyStatus.COMPLETE)))
                .organizerInfo(organizerLogRepositoryProvider.findByOrganizerIdPartyStatusIn(kakaoId, List.of(PartyStatus.MATCHING, PartyStatus.COMPLETE)))
                .build();
    }

    /***
     * > 파티정보 등록
     * 
     * @param reqOrganizerLog
     * @return
     */
    public OrganizerLog saveOrganizerInfo(OrganizerLog reqOrganizerLog) {
        // TODO: 파티정보 등록 시 사전 비즈니스 검증로직
        
        return organizerLogRepositoryProvider.save(reqOrganizerLog)
                .orElseThrow(() -> new RuntimeException()); // TODO: save 실패 시 로깅이나 유연하고 명시적인 예외처리 가능하도록
    }
}
