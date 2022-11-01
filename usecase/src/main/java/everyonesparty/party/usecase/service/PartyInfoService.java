package everyonesparty.party.usecase.service;

import everyonesparty.party.usecase.domain.CurrentUserPartyInfo;
import everyonesparty.party.usecase.domain.MemberLog;
import everyonesparty.party.usecase.domain.OrganizerLog;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import everyonesparty.party.usecase.repositoryprovider.MemberLogRepositoryProvider;
import everyonesparty.party.usecase.repositoryprovider.OrganizerLogRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .organizerInfo(organizerLogRepositoryProvider.findByOrganizerIdAndPartyStatusIn(kakaoId, List.of(PartyStatus.MATCHING, PartyStatus.COMPLETE)))
                .build();
    }

    /***
     * > 파티정보 등록
     * 
     * @param reqOrganizerLog
     * @return
     */
    public OrganizerLog saveOrganizerInfo(OrganizerLog reqOrganizerLog) {
        if(isExistPartyInfo(reqOrganizerLog.getOrganizerId(), reqOrganizerLog.getOttName())){
            throw new RuntimeException();   // TODO: 유연한 예외처리 + 비즈니스 예외를 만들어 던지기
        }

        return organizerLogRepositoryProvider.save(reqOrganizerLog)
                .orElseThrow(() -> new RuntimeException()); // TODO: save 실패 시 로깅이나 유연하고 명시적인 예외처리 가능하도록
    }

    /***
     * > 파티정보를 등록하려는 사용자가 동일한 ott 의 {파티장 or 파티원}으로 {매칭중 or 완료}된 기록이 있나? -> 있으면 true
     *
     * @param organizerId
     * @param ottName
     * @return
     */
    private boolean isExistPartyInfo(String organizerId, OttName ottName) {
        List<OrganizerLog> existOrganizerInfo = organizerLogRepositoryProvider
                .findByOrganizerIdAndOttNameAndPartyStatusIn(organizerId, ottName, List.of(PartyStatus.COMPLETE, PartyStatus.MATCHING));
        List<MemberLog> existMemberInfo = memberLogRepositoryProvider
                .findByMemberIdAndOttNameAndPartyStatusIn(organizerId, ottName, List.of(PartyStatus.COMPLETE, PartyStatus.MATCHING));

        return !(existOrganizerInfo.isEmpty() && existMemberInfo.isEmpty());
    }
}
