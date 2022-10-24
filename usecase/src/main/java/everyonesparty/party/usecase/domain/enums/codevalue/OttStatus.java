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
public enum OttStatus implements CodeValue {
    IMMEDIATE_MATCHING("01","즉시매칭 가능"),
    NORMAL("02","보통"),
    CONGESTION("03","혼잡");

    private String code;
    private String value;

    /***
     * > code 에 해당하는 OttName 을 찾아서 리턴
     * > 없을 시 NoSuchElementException() 을 던짐
     * @param code
     * @return
     */
    public static OttStatus find(String code) {
        return Stream.of(OttStatus.values())
                .filter(ottStatus -> ottStatus.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
