package assignment.wanted_pre_onboarding.controller.company.dto.request;

import lombok.Getter;

@Getter
public class CreateCompanyRequest {
    private String companyName;
    private String country;
    private String region;
}