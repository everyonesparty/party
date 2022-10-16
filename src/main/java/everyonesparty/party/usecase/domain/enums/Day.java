package everyonesparty.party.usecase.domain.enums;

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
public enum Day implements CodeValue{

    ;   // TODO: 날짜 부터 다시 도메인 & 엔티티 설계

    private String code;
    private String value;

    /***
     * > code 에 해당하는 MatchMethod 을 찾아서 리턴
     * > 없을 시 NoSuchElementException() 을 던짐
     * @param code
     * @return
     */
    public static Day find(String code) {
        return Stream.of(Day.values())
                .filter(day -> day.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
