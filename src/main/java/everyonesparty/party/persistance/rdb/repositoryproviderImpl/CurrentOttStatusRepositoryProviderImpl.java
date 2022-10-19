package everyonesparty.party.persistance.rdb.repositoryproviderImpl;

import everyonesparty.party.persistance.rdb.entity.CurrentOttStatusEntity;
import everyonesparty.party.persistance.rdb.repository.CurrentOttStatusRepository;
import everyonesparty.party.usecase.domain.CurrentOttStatus;
import everyonesparty.party.usecase.repositoryprovider.CurrentOttStatusRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CurrentOttStatusRepositoryProviderImpl implements CurrentOttStatusRepositoryProvider {

    private final CurrentOttStatusRepository currentOttStatusRepository;

    public Optional<CurrentOttStatus> findByOttName(String ottName) {
        return currentOttStatusRepository.findByOttName(ottName)
                .map(CurrentOttStatusEntity::toDomain);
    }

    public CurrentOttStatus save(CurrentOttStatus currentOttStatus) {
        return currentOttStatusRepository.save(CurrentOttStatusEntity.fromDomain(currentOttStatus)).toDomain();
    }
}
