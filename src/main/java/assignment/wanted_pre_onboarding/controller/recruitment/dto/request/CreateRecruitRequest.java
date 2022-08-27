package assignment.wanted_pre_onboarding.controller.recruitment.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRecruitRequest {

    private Long companyId;
    private String position;
    private Integer reward;
    private String contents;
    private String techInfo;

    @Builder
    public CreateRecruitRequest(Long companyId, String position, Integer reward, String contents, String techInfo) {
        this.companyId = companyId;
        this.position = position;
        this.reward = reward;
        this.contents = contents;
        this.techInfo = techInfo;
    }
}
