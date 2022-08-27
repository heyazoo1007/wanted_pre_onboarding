package assignment.wanted_pre_onboarding.common.exception.model;

import assignment.wanted_pre_onboarding.common.exception.ErrorCode;

public class InternalServerException extends WantedException {

    public InternalServerException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InternalServerException(String message) {
        super(message, ErrorCode.INTERNAL_SERVER_EXCEPTION);
    }
}
