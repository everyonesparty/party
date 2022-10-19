package everyonesparty.party.usecase.domain.enums.codevalue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/***
 * > immutable 하게 유지
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MatchMethod implements CodeValue{

    AUTO("01", "자동매칭"),
    MANUALLY("02", "직접초대");

    private String code;
    private String value;

    /***
     * > code 에 해당하는 MatchMethod 을 찾아서 리턴
     * > 없을 시 NoSuchElementException() 을 던짐
     * @param code
     * @return
     */
    public static MatchMethod find(String code) {
        return Stream.of(MatchMethod.values())
                .filter(matchMethod -> matchMethod.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
