package everyonesparty.party.persistance.rdb.repository;

import everyonesparty.party.persistance.rdb.entity.MemberLogEntity;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("codetest") // test profile 로 설정해서 {}-codetest.yml 을 바라보게 만듦
@DataJpaTest
class MemberLogRepositoryTest {

    @Autowired
    private MemberLogRepository memberLogRepository;

    @Test
    public void findByMemberIdAndPartyStatus() {
        // given
        final String memberId = "2012211";
        MemberLogEntity givenMemberLogEntity1 = MemberLogEntity.builder()
                .ottName(OttName.DISNEY)
                .partyStatus(PartyStatus.CANCEL)
                .memberId(memberId)
                .cardId(12L)
                .build();

        MemberLogEntity givenMemberLogEntity2 = MemberLogEntity.builder()
                .ottName(OttName.NETFLIX)
                .partyStatus(PartyStatus.MATCHING)
                .memberId(memberId)
                .cardId(12L)
                .build();

        MemberLogEntity givenMemberLogEntity3 = MemberLogEntity.builder()
                .ottName(OttName.WATCHA)
                .partyStatus(PartyStatus.MATCHING)
                .memberId(memberId)
                .cardId(12L)
                .build();

        MemberLogEntity givenMemberLogEntity4 = MemberLogEntity.builder()
                .ottName(OttName.TVING)
                .partyStatus(PartyStatus.COMPLETE)
                .memberId(memberId)
                .cardId(12L)
                .build();

        memberLogRepository.save(givenMemberLogEntity1);
        memberLogRepository.save(givenMemberLogEntity2);
        memberLogRepository.save(givenMemberLogEntity3);
        memberLogRepository.save(givenMemberLogEntity4);

        // when
        List<MemberLogEntity> resList = memberLogRepository.findByMemberIdAndPartyStatusIn(memberId, List.of(PartyStatus.MATCHING, PartyStatus.CANCEL));

        // then
        List<PartyStatus> partyStatuses = resList.stream()
                .map(memberLogEntity -> memberLogEntity.getPartyStatus())
                .collect(Collectors.toList());

        assertTrue(partyStatuses.contains(PartyStatus.MATCHING));
        assertTrue(partyStatuses.contains(PartyStatus.CANCEL));
    }
}