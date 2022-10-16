package everyonesparty.party.presentation.exception.advice;

import everyonesparty.party.presentation.exception.PresentationException;
import everyonesparty.party.presentation.exception.error.CommonError;
import everyonesparty.party.presentation.exception.error.PresentaionErrorMap;
import everyonesparty.party.presentation.exception.error.PresentationError;
import everyonesparty.party.presentation.response.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/***
 * > 객체 검증 과정에서 발생할 수 있는 에러에 관한 이야기들
 *      > https://kapentaz.github.io/spring/Spring-Boo-Bean-Validation-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90/#
 *      
 */
@Slf4j
@RestControllerAdvice(basePackages="everyonesparty.auth")
public class PresentationControllerAdvice {

    /***
     * > 가장 상위의 예외 핸들러
     *      > 하위 타입의 예외가 발생하면 여기가 아니라 그 하위 타입의 헨들러 메소드로 메핑된다는 가정 하에 구현
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> superExceptionHandler(Exception ex) {
        log.error("Exception: ",  ex);
        return ResponseUtils.out(CommonError.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PresentationException.class)
    public ResponseEntity<?> logicalRuntimeExceptionHandler(PresentationException ex) {
        log.info("LogicalRuntimeException: {}",  ex.getPresentationError().getErrorMsg());
        return ResponseUtils.out(ex.getPresentationError());
    }

    /***
     * 요청 데이터를 객체로 변환하지 못할 경우 발생하는 예외에 대한 핸들러
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException: ",  ex);
        return ResponseUtils.out(CommonError.BAD_REQUEST);
    }

    /***
     * 요청 데이터를 객채가 아니라 바로 파라미터로 받을 경우, 검증 과정에서 오류가 발생한 경우
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error("ConstraintViolationException: ",  ex);
        return ResponseUtils.out(CommonError.BAD_REQUEST);
    }

    /***
     * 요청 데이터를 객체로 변환했으나 검증 과정에서 실패한 예외에 대한 핸들러
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException: ",  ex);

        //TODO: 별도의 ConstraintValidator 가 없을 경우 동작 방식 확인 필요
        try {
            // 별도의 ConstraintValidator에서 발생한 검증 오류의 경우 넘어온 에러 객체로 응답
            ConstraintViolation<?> constraintViolation = ex.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);
            String errorKey = constraintViolation.getMessage();
            PresentationError error = PresentaionErrorMap.getError(errorKey);
            if(error == null)
                return ResponseUtils.out(CommonError.BAD_REQUEST);
            else
                return ResponseUtils.out(error);
        }catch(Exception e) {
            return ResponseUtils.out(CommonError.BAD_REQUEST);
        }
    }

    /***
     * [multipart/form-data] 요청에 대해
     * RequestPart(required=true) 필드 값이 안들어 온 경우 예외에 대한 핸들러
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    protected ResponseEntity<?> missingServletRequestPartExceptionHandler(MissingServletRequestPartException ex) {
        log.error("MissingServletRequestPartException: ",  ex);
        return ResponseUtils.out(CommonError.BAD_REQUEST);
    }
}
