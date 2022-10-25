package everyonesparty.party.persistance.rdb;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * > persistence - layer 분리 시 독립적인 테스트를 위한 설정
 * > @SpringBootApplication 이 있어야 관련 설정 정보들이 로딩되고 하위 페키지에 대한 component scan 이 발생함
 * > 참고: https://www.baeldung.com/spring-boot-unable-to-find-springbootconfiguration-with-datajpatest
 */
@SpringBootApplication
public class TestApplication {

}