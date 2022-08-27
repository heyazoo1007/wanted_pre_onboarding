package assignment.wanted_pre_onboarding.common.exception.model;

import assignment.wanted_pre_onboarding.common.exception.ErrorCode;

public class NotFoundException extends WantedException {

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message) {
        super(message, ErrorCode.NOT_FOUND_EXCEPTION);
    }
}
