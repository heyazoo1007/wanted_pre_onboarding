package assignment.wanted_pre_onboarding.web.recruitment.dto.response;

import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.recruiment.Recruitment;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetRecruitListResponse {

    private Long recruitId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer reward;
    private String techInfo;

    @Builder
    public GetRecruitListResponse(Long recruitId, String companyName, String country, String region, String position, Integer reward, String techInfo) {
        this.recruitId = recruitId;
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.position = position;
        this.reward = reward;
        this.techInfo = techInfo;
    }

    public static GetRecruitListResponse of(Company company, Recruitment recruitment) {
        GetRecruitListResponse response = GetRecruitListResponse.builder()
                .recruitId(recruitment.getId())
                .companyName(company.getCompanyName())
                .country(company.getCountry())
                .region(company.getRegion())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .techInfo(recruitment.getTechInfo())
                .build();

        return response;
    }
}
