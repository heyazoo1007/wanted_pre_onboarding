package assignment.wanted_pre_onboarding.web.recruitment.dto.response;

import assignment.wanted_pre_onboarding.domain.recruiment.Recruitment;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveRecruitResponse {

    private Long id;
    private String position;
    private Integer reward;
    private String contents;
    private String techInfo;

    @Builder
    public SaveRecruitResponse(Long id, String position, Integer reward, String contents, String techInfo) {
        this.id = id;
        this.position = position;
        this.reward = reward;
        this.contents = contents;
        this.techInfo = techInfo;
    }

    public static SaveRecruitResponse of(Recruitment recruitment) {
        SaveRecruitResponse response = SaveRecruitResponse.builder()
                .id(recruitment.getCompany_id())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .contents(recruitment.getContents())
                .techInfo(recruitment.getTechInfo())
                .build();
        return response;
    }
}
