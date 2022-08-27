package assignment.wanted_pre_onboarding.common.exception.model;

import assignment.wanted_pre_onboarding.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class WantedException extends RuntimeException {

    private final ErrorCode errorCode;

    public WantedException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() { return errorCode.getStatus(); }
}
