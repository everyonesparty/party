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
public enum PartyStatus implements CodeValue{

    MATCHING("01", "모집중"),
    COMPLETE("02", "모집완료"),
    CANCEL("03", "취소");

    private String code;
    private String value;

    /***
     * > code 에 해당하는 PartyStatus 을 찾아서 리턴
     * > 없을 시 NoSuchElementException() 을 던짐
     * @param code
     * @return
     */
    public static PartyStatus find(String code) {
        return Stream.of(PartyStatus.values())
                .filter(partyStatus -> partyStatus.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
