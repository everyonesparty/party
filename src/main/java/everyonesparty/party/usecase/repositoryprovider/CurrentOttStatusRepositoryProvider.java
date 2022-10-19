package everyonesparty.party.usecase.repositoryprovider;

import everyonesparty.party.usecase.domain.CurrentOttStatus;

import java.util.Optional;

public interface CurrentOttStatusRepositoryProvider {

    Optional<CurrentOttStatus> findByOttName(String ottName);

    CurrentOttStatus save(CurrentOttStatus currentOttStatus);
}
