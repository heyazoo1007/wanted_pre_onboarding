package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.company.CompanyRepository;
import assignment.wanted_pre_onboarding.web.company.dto.request.CreateCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void createCompany(CreateCompanyRequest request) {

        Company created = Company.of(request.getCompanyName(), request.getCountry(), request.getRegion());

        companyRepository.save(created);
    }
}
