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
public enum OttName implements CodeValue {
    NETFLIX("01", "넷플릭스"),
    WAVVE("02", "웨이브"),
    WATCHA("03", "왓챠"),
    LAFTEL("04", "라프텔"),
    TVING("05", "티빙"),
    DISNEY("06", "디즈니");

    private String code;
    private String value;

    /***
     * > code 에 해당하는 OttName 을 찾아서 리턴
     * > 없을 시 NoSuchElementException() 을 던짐
     * @param code
     * @return
     */
    public static OttName find(String code) {
        return Stream.of(OttName.values())
                .filter(ottName -> ottName.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
