package everyonesparty.party.presentation.response;

import everyonesparty.party.presentation.exception.error.PresentationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
    private T data;
    private PresentationError error;

    public boolean hasError(){
        return (error != null);
    }
}
