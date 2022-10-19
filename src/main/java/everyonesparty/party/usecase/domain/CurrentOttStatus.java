package everyonesparty.party.usecase.domain;

import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.OttStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/***
 * > 최대한 immutable 하게 보호
 */
@Getter
@AllArgsConstructor
@Builder
public class CurrentOttStatus {

    private OttName ottName;

    private Boolean isUsed;

    private OttStatus ottStatus;
}
