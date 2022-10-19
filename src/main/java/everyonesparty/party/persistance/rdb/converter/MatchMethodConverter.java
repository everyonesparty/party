package everyonesparty.party.persistance.rdb.converter;

import everyonesparty.party.usecase.domain.enums.codevalue.MatchMethod;

/***
 * > jpa entity <-> db 사이에서 Enum mapping
 * > 참고: https://velog.io/@youmakemesmile/JPA-Enum-Type-%EC%A0%81%EC%9A%A9%EA%B8%B0
 */
public class MatchMethodConverter extends CodeValueConverter<MatchMethod> {
    MatchMethodConverter() {
        super(MatchMethod.class);
    }
}
