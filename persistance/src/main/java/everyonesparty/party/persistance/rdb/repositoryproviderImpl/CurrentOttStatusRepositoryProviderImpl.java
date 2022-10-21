package everyonesparty.party.persistance.rdb.repositoryproviderImpl;

import everyonesparty.party.persistance.rdb.entity.CurrentOttStatusEntity;
import everyonesparty.party.persistance.rdb.repository.CurrentOttStatusRepository;
import everyonesparty.party.usecase.domain.CurrentOttStatus;
import everyonesparty.party.usecase.repositoryprovider.CurrentOttStatusRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CurrentOttStatusRepositoryProviderImpl implements CurrentOttStatusRepositoryProvider {

    private final CurrentOttStatusRepository currentOttStatusRepository;

    @Override
    public List<CurrentOttStatus> findAll() {
        return currentOttStatusRepository.findAll().stream()
                .map(CurrentOttStatusEntity::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<CurrentOttStatus> findByOttName(String ottName) {
        return currentOttStatusRepository.findByOttName(ottName)
                .map(CurrentOttStatusEntity::toDomain);
    }

    public Optional<CurrentOttStatus> save(CurrentOttStatus currentOttStatus) {
        return Optional.ofNullable(currentOttStatusRepository.save(CurrentOttStatusEntity.fromDomain(currentOttStatus)))
                .map(CurrentOttStatusEntity::toDomain);
    }
}
