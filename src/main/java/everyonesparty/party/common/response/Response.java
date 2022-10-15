package everyonesparty.party.common.response;

import everyonesparty.party.common.exception.error.RestError;
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
    private RestError error;

    public boolean hasError(){
        return (error != null);
    }
}
