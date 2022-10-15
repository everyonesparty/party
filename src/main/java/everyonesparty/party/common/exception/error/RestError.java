package everyonesparty.party.common.exception.error;

import everyonesparty.party.common.response.ResponseError;
import org.springframework.http.HttpStatus;

public interface RestError {

    ResponseError toResponseError();

    HttpStatus getHttpStatus();

    String getErrorMsg();

}
