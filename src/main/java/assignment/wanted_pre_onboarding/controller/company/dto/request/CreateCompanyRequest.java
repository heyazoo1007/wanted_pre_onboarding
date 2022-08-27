package assignment.wanted_pre_onboarding.controller.company.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
public class CreateCompanyRequest {
    private String companyName;
    private String country;
    private String region;

    public CreateCompanyRequest(String companyName, String country, String region) {
        this.companyName = companyName;
        this.country = country;
        this.region = region;
    }
}
