package everyonesparty.party.persistance.rdb.repositoryproviderImpl;

import everyonesparty.party.persistance.rdb.entity.OrganizerLogEntity;
import everyonesparty.party.persistance.rdb.repository.OrganizerLogRepository;
import everyonesparty.party.usecase.domain.enums.codevalue.Date;
import everyonesparty.party.usecase.domain.enums.codevalue.MatchMethod;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.PartyStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("codetest") // test profile 로 설정해서 {}-codetest.yml 을 바라보게 만듦
@DataJpaTest
class OrganizerLogRepositoryProviderImplTest {

    // TODO: @DataJpaTest 는 JPA 관련 놈들만 인식함 -> ProviderImpl 은 이것과 무관한 놈들인데 이걸 어떻게 테스트할지?
    // TODO: 참고 => https://stackoverflow.com/questions/48347088/spring-test-with-datajpatest-cant-autowire-class-with-repository-but-with-in
//    @Autowired
//    private OrganizerLogRepositoryProviderImpl organizerLogRepositoryProviderImpl;

    @Autowired
    private OrganizerLogRepository organizerLogRepository;

    @Test
    void 기본키_생성전략_테스트() {
        // given
        OrganizerLogEntity givenOrganizerLogEntity1 = OrganizerLogEntity.builder()
                .memberCount(4)
                .matchMethod(MatchMethod.AUTO)
                .settlementDate(Date.EIGHTH)
                .accountId(0L)
                .ottName(OttName.DISNEY)
                .ottId("gshgsh0831@gmail.com")
                .ottPassword("testPassword")
                .partyStatus(PartyStatus.MATCHING)
                .organizerId("testKakaoId")
                .build();

        OrganizerLogEntity givenOrganizerLogEntity2 = OrganizerLogEntity.builder()
                .memberCount(4)
                .matchMethod(MatchMethod.AUTO)
                .settlementDate(Date.EIGHTH)
                .accountId(0L)
                .ottName(OttName.DISNEY)
                .ottId("gshgsh0831@gmail.com")
                .ottPassword("testPassword")
                .partyStatus(PartyStatus.MATCHING)
                .organizerId("testKakaoId")
                .build();

        // when
        OrganizerLogEntity organizerLog1 = organizerLogRepository.save(givenOrganizerLogEntity1);
        OrganizerLogEntity organizerLog2 = organizerLogRepository.save(givenOrganizerLogEntity2);

        // then
        assertEquals(organizerLog1.getId(), 1L);

        assertEquals(organizerLog2.getId(), 2L);
    }
}