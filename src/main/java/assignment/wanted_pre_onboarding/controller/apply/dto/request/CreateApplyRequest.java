package assignment.wanted_pre_onboarding.controller.apply.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateApplyRequest {
    private Long recruitId;
    private Long userId;

    @Builder
    public CreateApplyRequest(Long recruitId, Long userId) {
        this.recruitId = recruitId;
        this.userId = userId;
    }
}
