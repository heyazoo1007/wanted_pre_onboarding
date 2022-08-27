package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.common.exception.model.ConflictException;
import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.company.CompanyRepository;
import assignment.wanted_pre_onboarding.controller.company.dto.request.CreateCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static assignment.wanted_pre_onboarding.common.exception.ErrorCode.CONFLICT_EXCEPTION;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public void createCompany(CreateCompanyRequest request) {
        String companyName = request.getCompanyName();
        Optional<Company> company = companyRepository.findByCompanyName(companyName);

        if (company.isPresent()) {
            throw new ConflictException("이미 등록된 회사입니다.", CONFLICT_EXCEPTION);
        }
        Company created = Company.of(request.getCompanyName(), request.getCountry(), request.getRegion());

        companyRepository.save(created);
    }
}
