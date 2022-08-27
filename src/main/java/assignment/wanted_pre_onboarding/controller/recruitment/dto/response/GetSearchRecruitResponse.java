package assignment.wanted_pre_onboarding.controller.recruitment.dto.response;


import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetSearchRecruitResponse {

    private Long recruitId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer reward;
    private String techInfo;

    @Builder
    public GetSearchRecruitResponse(Long recruitId, String companyName, String country, String region, String position, Integer reward, String techInfo) {
        this.recruitId = recruitId;
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.position = position;
        this.reward = reward;
        this.techInfo = techInfo;
    }

    public static GetSearchRecruitResponse of(Recruitment recruitment) {
        GetSearchRecruitResponse response = GetSearchRecruitResponse.builder()
                .recruitId(recruitment.getId())
                .companyName(recruitment.getCompany().getCompanyName())
                .country(recruitment.getCompany().getCountry())
                .region(recruitment.getCompany().getRegion())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .techInfo(recruitment.getTechInfo())
                .build();

        return response;
    }
}
