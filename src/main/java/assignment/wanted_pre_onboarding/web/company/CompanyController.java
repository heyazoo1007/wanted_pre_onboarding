package assignment.wanted_pre_onboarding.web.company;

import assignment.wanted_pre_onboarding.common.ApiResponse;
import assignment.wanted_pre_onboarding.domain.company.CompanyRepository;
import assignment.wanted_pre_onboarding.service.CompanyService;
import assignment.wanted_pre_onboarding.web.company.dto.request.CreateCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/create")
    public ApiResponse<String> createCompany(@RequestBody CreateCompanyRequest request) {
        companyService.createCompany(request);
        return ApiResponse.SUCCESS;
    }


}
