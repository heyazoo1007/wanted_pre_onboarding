package assignment.wanted_pre_onboarding.controller.recruitment.dto.response;

import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateRecruitResponse {

    private String position;
    private Integer reward;
    private String contents;
    private String techInfo;

    @Builder
    public UpdateRecruitResponse(String position, Integer reward, String contents, String techInfo) {
        this.position = position;
        this.reward = reward;
        this.contents = contents;
        this.techInfo = techInfo;
    }

    public static UpdateRecruitResponse of(Recruitment recruitment) {
        UpdateRecruitResponse response = UpdateRecruitResponse.builder()
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .contents(recruitment.getContents())
                .techInfo(recruitment.getTechInfo())
                .build();
        return response;
    }
}
