package assignment.wanted_pre_onboarding.controller.advice;

import assignment.wanted_pre_onboarding.common.dto.ApiResponse;
import assignment.wanted_pre_onboarding.common.exception.ErrorCode;
import assignment.wanted_pre_onboarding.common.exception.model.WantedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static assignment.wanted_pre_onboarding.common.exception.ErrorCode.INTERNAL_SERVER_EXCEPTION;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 Bad Request
     * Spring Validation
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    protected ApiResponse<Object> handleBadRequest(final BindException e) {
        log.error(e.getMessage());
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ApiResponse.error(ErrorCode.VALIDATION_EXCEPTION, String.format("%s (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    /**
     * Wanted Custom Exception
     */
    @ExceptionHandler(WantedException.class)
    protected ResponseEntity<ApiResponse<Object>> handleBaseException(WantedException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(e.getStatus())
                .body(ApiResponse.error(e.getErrorCode()));
    }

    /**
     * 500 Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiResponse<Object> handleException(final Exception e) {
        log.error(e.getMessage(), e);
        return ApiResponse.error(INTERNAL_SERVER_EXCEPTION);
    }
}
