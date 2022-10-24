package everyonesparty.party.presentation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import everyonesparty.party.presentation.dto.serializer.CodeValueSerializer;
import everyonesparty.party.usecase.domain.CurrentOttStatus;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.OttStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CurrentOttStatusDTO {

//    public static class Req {
//
//    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Res {

        @JsonSerialize(using = CodeValueSerializer.class)
        private OttName ottName;

        private Boolean isUsed;

        @JsonSerialize(using = CodeValueSerializer.class)
        private OttStatus ottStatus;

        private Long price;
        private Long organizerFee;
        private Long memberFee;

        public static Res fromDomian(CurrentOttStatus currentOttStatus) {
            return Res.builder()
                    .ottName(currentOttStatus.getOttName())
                    .isUsed(currentOttStatus.getIsUsed())
                    .ottStatus(currentOttStatus.getOttStatus())
                    .price(currentOttStatus.getPrice())
                    .organizerFee(currentOttStatus.getOrganizerFee())
                    .memberFee(currentOttStatus.getMemberFee())
                    .build();
        }
    }
}
