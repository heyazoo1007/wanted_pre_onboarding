package assignment.wanted_pre_onboarding.controller.recruitment.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteRecruitRequest {
    private Long recruitId;

    @Builder
    public DeleteRecruitRequest(Long recruitId) {
        this.recruitId = recruitId;
    }
}
