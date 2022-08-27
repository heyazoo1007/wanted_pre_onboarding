package assignment.wanted_pre_onboarding.controller.recruitment.dto.request;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateRecruitRequest {

    private Long recruitId;
    private String position;
    private Integer reward;
    private String contents;
    private String techInfo;

    @Builder
    public UpdateRecruitRequest(Long recruitId, String position, Integer reward, String contents, String techInfo) {
        this.recruitId = recruitId;
        this.position = position;
        this.reward = reward;
        this.contents = contents;
        this.techInfo = techInfo;
    }
}
