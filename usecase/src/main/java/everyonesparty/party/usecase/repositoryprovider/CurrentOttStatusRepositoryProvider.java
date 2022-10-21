package everyonesparty.party.usecase.repositoryprovider;

import everyonesparty.party.usecase.domain.CurrentOttStatus;

import java.util.List;
import java.util.Optional;

public interface CurrentOttStatusRepositoryProvider {

    List<CurrentOttStatus> findAll();   // list 는 레코드가 없을 경우 null 이 아니라 empty list 를 반환한다는 가정

    Optional<CurrentOttStatus> findByOttName(String ottName);

    Optional<CurrentOttStatus> save(CurrentOttStatus currentOttStatus);
}
