package everyonesparty.party.usecase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/***
 * > kakaoId 에 해당하는 사용자의 파티정보
 * > 최대한 immutable 하게 보호
 */
@Getter
@AllArgsConstructor
@Builder
public class UserPartyInfo {
    String kakaoId;
    List<MemberLog> memberInfo;
    List<OrganizerLog> organizerInfo;
}
