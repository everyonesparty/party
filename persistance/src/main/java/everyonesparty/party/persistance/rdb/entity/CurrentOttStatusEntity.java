package everyonesparty.party.persistance.rdb.entity;

import everyonesparty.party.usecase.domain.CurrentOttStatus;
import everyonesparty.party.usecase.domain.enums.codevalue.OttName;
import everyonesparty.party.usecase.domain.enums.codevalue.OttStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/***
 * > 각 ott 플랫폼의 현재 상태
 * > 상태는 특정 주기마다 갱신 -> 현황 성격으로 유지
 */
@Entity
@Table(name = "current_ott_status")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CurrentOttStatusEntity {

    @Id
    @NotNull
    @Column(name = "ott_name")
    @Enumerated(EnumType.STRING)
    private OttName ottName;

    @NotNull
    private Boolean isUsed;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OttStatus ottStatus;

    public CurrentOttStatus toDomain() {
        return CurrentOttStatus.builder()
                .ottName(this.ottName)
                .isUsed(this.isUsed)
                .ottStatus(this.ottStatus)
                .build();
    }

    public static CurrentOttStatusEntity fromDomain(CurrentOttStatus currentOttStatus) {
        return CurrentOttStatusEntity.builder()
                .ottName(currentOttStatus.getOttName())
                .isUsed(currentOttStatus.getIsUsed())
                .ottStatus(currentOttStatus.getOttStatus())
                .build();
    }
}
