package assignment.wanted_pre_onboarding.controller.recruitment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteRecruitRequest {
    private Long recruitId;

    @Builder
    public DeleteRecruitRequest(Long recruitId) {
        this.recruitId = recruitId;
    }
}
