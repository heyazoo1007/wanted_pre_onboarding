package assignment.wanted_pre_onboarding.web.recruitment.dto.request;

import lombok.Getter;

@Getter
public class SaveRecruitRequest {

    private String position;
    private Integer reward;
    private String contents;
    private String techInfo;

}
