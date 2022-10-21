package everyonesparty.party.presentation.exception.error;

import everyonesparty.party.presentation.response.ResponseError;
import org.springframework.http.HttpStatus;

public interface PresentationError {

    ResponseError toResponseError();

    HttpStatus getHttpStatus();

    String getErrorMsg();

}
