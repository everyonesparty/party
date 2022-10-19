package everyonesparty.party.usecase.service;

import everyonesparty.party.usecase.domain.CurrentOttStatus;
import everyonesparty.party.usecase.repositoryprovider.CurrentOttStatusRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CurrentOttStatusService {

    private final CurrentOttStatusRepositoryProvider currentOttStatusRepositoryProvider;

    public CurrentOttStatus findByOttName(String ottName) {
        return currentOttStatusRepositoryProvider.findByOttName(ottName)
                .orElseThrow(() -> new NoSuchElementException());
    }

    public CurrentOttStatus save(CurrentOttStatus currentOttStatus) {
        return currentOttStatusRepositoryProvider.save(currentOttStatus);
    }
}
