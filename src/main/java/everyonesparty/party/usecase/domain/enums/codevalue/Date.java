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
public enum Date implements CodeValue {

    FIRST("01", "1일"),
    SECOND("02","2일"),
    THIRD("03","3일"),
    FOURTH("04","4일"),
    FIFTH("05","5일"),
    SIXTH("06","6일"),
    SEVENTH("07","7일"),
    EIGHTH("08", "8일"),
    NINTH("09","9일"),
    TENTH("10","10일"),
    ELEVENTH("11","11일"),
    TWELFTH("12", "12일"),
    THIRTEENTH("13","13일"),
    FOURTEENTH("14","14일"),
    FIFTEENTH("15","15일"),
    SIXTEENTH("16","16일"),
    SEVENTEENTH("17","17일"),
    EIGHTEENTH("18","18일"),
    NINETEENTH("19","19일"),
    TWENTIETH("20","20일"),
    TWENTY_FIRST("21","21일"),
    TWENTY_SECOND("22","22일"),
    TWENTY_THIRD("23","23일"),
    TWENTY_FOURTH("24","24일"),
    TWENTY_FIFTH("25","25일"),
    TWENTY_SIXTH("26","26일"),
    TWENTY_SEVENTH("27","27일"),
    TWENTY_EIGHTH("28", "28일"),
    TWENTY_NINTH("29","29일"),
    THIRTIETH("30","30일"),
    THIRTY_FIRST("31","31일");

    private String code;
    private String value;

    /***
     * > code 에 해당하는 MatchMethod 을 찾아서 리턴
     * > 없을 시 NoSuchElementException() 을 던짐
     * @param code
     * @return
     */
    public static Date find(String code) {
        return Stream.of(Date.values())
                .filter(date -> date.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
