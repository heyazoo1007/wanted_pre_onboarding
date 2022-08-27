package assignment.wanted_pre_onboarding.controller.recruitment.dto.response;

import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetRecruitDetailResponse {

    private Long recruitId;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer reward;
    private String techInfo;
    private String contents;
    private List<Long> companyRecruits = new ArrayList<>();

    @Builder
    public GetRecruitDetailResponse(Long recruitId, String companyName, String country, String region, String position, Integer reward, String techInfo, String contents, List<Long> companyRecruits) {
        this.recruitId = recruitId;
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.position = position;
        this.reward = reward;
        this.techInfo = techInfo;
        this.contents = contents;
        this.companyRecruits = companyRecruits;
    }

    public static GetRecruitDetailResponse of(Company company, Recruitment recruitment, List<Long> companyRecruits) {
        GetRecruitDetailResponse response = GetRecruitDetailResponse.builder()
                .recruitId(recruitment.getId())
                .companyName(company.getCompanyName())
                .country(company.getCountry())
                .region(company.getRegion())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .techInfo(recruitment.getTechInfo())
                .contents(recruitment.getContents())
                .companyRecruits(companyRecruits)
                .build();

        return response;
    }
}
