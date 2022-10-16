package everyonesparty.party.persistance.rdb.entity;

import everyonesparty.party.persistance.rdb.converter.MatchMethodConverter;
import everyonesparty.party.usecase.domain.enums.MatchMethod;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * > 파티장 로그
 */
@Entity
@Table(name = "organizer_log_table")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrganizerLogEntity {

    @Id
    private Long id;

    private Integer memberCount;

    @Convert(converter = MatchMethodConverter.class)
    private MatchMethod matchMethod;


}
