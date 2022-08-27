package assignment.wanted_pre_onboarding.common.exception.model;

import assignment.wanted_pre_onboarding.common.exception.ErrorCode;

public class ConflictException extends WantedException {

    public ConflictException(String message, ErrorCode errorCode) { super(message, errorCode); }

    public ConflictException(String message) { super(message, ErrorCode.CONFLICT_EXCEPTION); }
}
