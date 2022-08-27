package assignment.wanted_pre_onboarding.controller.recruitment.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateRecruitRequest {

    private Long recruitId;
    private String position;
    private Integer reward;
    private String contents;
    private String techInfo;
}
