package wagner.spo.challenge.optimizer.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import wagner.spo.challenge.optimizer.error.exception.CleanerException;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        final List<ApiSubError> subErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ApiSubError::fromFieldError)
                .collect(Collectors.toList());

        ApiError apiError = new ApiError("Request with missing or invalid fields", subErrors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CleanerException.class)
    protected ApiError handleCleanerException(CleanerException ex) {
        return new ApiError(ex.getMessage());
    }
}
