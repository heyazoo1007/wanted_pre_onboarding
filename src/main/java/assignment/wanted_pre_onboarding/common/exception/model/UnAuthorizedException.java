package assignment.wanted_pre_onboarding.common.exception.model;

import assignment.wanted_pre_onboarding.common.exception.ErrorCode;

public class UnAuthorizedException extends WantedException {

    public UnAuthorizedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
