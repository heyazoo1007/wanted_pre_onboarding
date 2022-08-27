package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.common.exception.model.ConflictException;
import assignment.wanted_pre_onboarding.controller.company.dto.request.CreateCompanyRequest;
import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.company.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void cleanUp() {
        companyRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("성공_회사_생성")
    void 회사_생성_성공() {
        //given
        CreateCompanyRequest request = new CreateCompanyRequest("company1", "korea", "seoul");

        companyService.createCompany(request);

        //when
        Optional<Company> findCompany = companyRepository.findByCompanyName(request.getCompanyName());

        //then
        //assertThat(actual).isEqualTo(expected)
        assertAll(
                () -> assertThat(findCompany.get().getCompanyName()).isEqualTo(request.getCompanyName()),
                () -> assertThat(findCompany.get().getCountry()).isEqualTo(request.getCountry()),
                () -> assertThat(findCompany.get().getRegion()).isEqualTo(request.getRegion()));
    }

    @Test
    @DisplayName("실패_중복된_회사_생성시")
    void 실패_중복된_회사_생성시() {
        //given
        CreateCompanyRequest request1 = new CreateCompanyRequest("company1", "korea", "seoul");
        CreateCompanyRequest request2 = new CreateCompanyRequest("company1", "korea", "seoul");

        companyService.createCompany(request1);

        //then
        assertThrows(ConflictException.class, () -> companyService.createCompany(request2));
    }
}
