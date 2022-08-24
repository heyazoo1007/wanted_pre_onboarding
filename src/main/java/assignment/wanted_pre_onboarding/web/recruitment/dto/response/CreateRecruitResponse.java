package assignment.wanted_pre_onboarding.web.recruitment.dto.response;

import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRecruitResponse {

    private Long id;
    private String position;
    private Integer reward;
    private String contents;
    private String techInfo;

    @Builder
    public CreateRecruitResponse(Long id, String position, Integer reward, String contents, String techInfo) {
        this.id = id;
        this.position = position;
        this.reward = reward;
        this.contents = contents;
        this.techInfo = techInfo;
    }

    public static CreateRecruitResponse of(Recruitment recruitment) {
        CreateRecruitResponse response = CreateRecruitResponse.builder()
                .id(recruitment.getId())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .contents(recruitment.getContents())
                .techInfo(recruitment.getTechInfo())
                .build();
        return response;
    }
}
