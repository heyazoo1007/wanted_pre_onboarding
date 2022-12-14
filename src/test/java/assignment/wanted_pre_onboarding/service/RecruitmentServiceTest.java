package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.common.exception.model.NotFoundException;
import assignment.wanted_pre_onboarding.controller.company.dto.request.CreateCompanyRequest;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.request.CreateRecruitRequest;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.request.DeleteRecruitRequest;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.request.UpdateRecruitRequest;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.response.CreateRecruitResponse;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.response.GetRecruitDetailResponse;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.response.GetRecruitListResponse;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.response.GetSearchRecruitResponse;
import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.company.CompanyRepository;
import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import assignment.wanted_pre_onboarding.domain.recruitment.RecruitmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RecruitmentServiceTest {

    @Autowired
    private RecruitmentService recruitmentService;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void cleanUp() {
        recruitmentRepository.deleteAllInBatch();
        companyRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("createRecruit_??????_????????????_??????")
    void createRecruit_??????_????????????_??????() {
        //given
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest("company1", "Korea", "Seoul");
        companyService.createCompany(createCompanyRequest);
        Optional<Company> findCompany = companyRepository.findByCompanyName(createCompanyRequest.getCompanyName());

        CreateRecruitRequest createRecruitRequest = CreateRecruitRequest.builder()
                .companyId(findCompany.get().getId())
                .position("Backend")
                .reward(2000000)
                .contents("now we are hiring,,,")
                .techInfo("Python")
                .build();

        CreateRecruitResponse createRecruitResponse = recruitmentService.createRecruit(createRecruitRequest);

        //when
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(createRecruitResponse.getRecruitId());

        //then, actual -> expected
        assertAll(
                () -> assertThat(findRecruit.get().getPosition()).isEqualTo(createRecruitRequest.getPosition()),
                () -> assertThat(findRecruit.get().getReward()).isEqualTo(createRecruitRequest.getReward()),
                () -> assertThat(findRecruit.get().getContents()).isEqualTo(createRecruitRequest.getContents()),
                () -> assertThat(findRecruit.get().getTechInfo()).isEqualTo(createRecruitRequest.getTechInfo()),
                () -> assertThat(findRecruit.get().getCompany().getId()).isEqualTo(createRecruitRequest.getCompanyId())
        );
    }

    @Test
    @DisplayName("createRecruit_??????_??????_????????????_????????????_??????")
    void createRecruit_??????_????????????_?????????_????????????_??????() {
        //given
        CreateRecruitRequest createRecruitRequest = CreateRecruitRequest.builder()
                .companyId(1L)
                .position("Backend")
                .reward(2000000)
                .contents("now we are hiring,,,")
                .techInfo("Python")
                .build();

        //then
        assertThrows(NotFoundException.class, () -> recruitmentService.createRecruit(createRecruitRequest));
    }

    @Test
    @DisplayName("updateRecruit_??????_????????????_??????")
    void updateRecruit_??????_????????????_??????() {
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest("company1", "Korea", "Seoul");
        companyService.createCompany(createCompanyRequest);
        Optional<Company> company = companyRepository.findByCompanyName(createCompanyRequest.getCompanyName());

        CreateRecruitRequest createRecruitRequest = CreateRecruitRequest.builder()
                .companyId(company.get().getId())
                .position("Backend")
                .reward(2000000)
                .contents("now we are hiring,,,")
                .techInfo("Python")
                .build();
        CreateRecruitResponse createRecruitResponse = recruitmentService.createRecruit(createRecruitRequest);
        Optional<Recruitment> recruit = recruitmentRepository.findById(createRecruitResponse.getRecruitId());

        UpdateRecruitRequest updateRecruitRequest = UpdateRecruitRequest.builder()
                .recruitId(recruit.get().getId())
                .position("Backend Junior Developer")
                .reward(15000000)
                .contents("now we are hiring Backend Junior Developer")
                .techInfo("Java")
                .build();

        //when
        recruitmentService.updateRecruit(updateRecruitRequest);

        //then
        Optional<Recruitment> updatedRecruit = recruitmentRepository.findById(recruit.get().getId());

        assertAll(
                () -> assertEquals(updateRecruitRequest.getRecruitId(), updatedRecruit.get().getId()),
                () -> assertEquals(updateRecruitRequest.getPosition(), updatedRecruit.get().getPosition()),
                () -> assertEquals(updateRecruitRequest.getReward(), updatedRecruit.get().getReward()),
                () -> assertEquals(updateRecruitRequest.getContents(), updatedRecruit.get().getContents()),
                () -> assertEquals(updateRecruitRequest.getTechInfo(), updatedRecruit.get().getTechInfo())
        );
    }

    @Test
    @DisplayName("updateRecruit_??????_????????????_??????_?????????_??????")
    void updateRecruit_??????_????????????_??????_?????????_??????() {
        UpdateRecruitRequest updateRecruitRequest = UpdateRecruitRequest.builder()
                .recruitId(1L)
                .position("Backend Junior Developer")
                .reward(15000000)
                .contents("now we are hiring Backend Junior Developer")
                .techInfo("Java")
                .build();

        //when

        //then
        assertThrows(NotFoundException.class, () -> recruitmentService.updateRecruit(updateRecruitRequest));
    }

    @Test
    @DisplayName("deleteRecruit_??????_????????????_??????")
    void deleteRecruit_??????_????????????_??????() {
        //given
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest("company1", "Korea", "Seoul");
        companyService.createCompany(createCompanyRequest);
        Optional<Company> findCompany = companyRepository.findByCompanyName(createCompanyRequest.getCompanyName());

        CreateRecruitRequest createRecruitRequest = CreateRecruitRequest.builder()
                .companyId(findCompany.get().getId())
                .position("Backend")
                .reward(2000000)
                .contents("now we are hiring,,,")
                .techInfo("Python")
                .build();
        CreateRecruitResponse createRecruitResponse = recruitmentService.createRecruit(createRecruitRequest);

        DeleteRecruitRequest deleteRecruitRequest = DeleteRecruitRequest.builder()
                .recruitId(createRecruitResponse.getRecruitId())
                .build();

        //when
        recruitmentService.deleteRecruit(deleteRecruitRequest);

        //then
        List<Recruitment> all = recruitmentRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("deleteRecruit_??????_??????????????????_????????????_??????")
    void deleteRecruit_??????_??????????????????_????????????_??????() {
        //given
        DeleteRecruitRequest deleteRecruitRequest = DeleteRecruitRequest.builder()
                .recruitId(1L)
                .build();

        //when

        //then
        assertThrows(NotFoundException.class, () -> recruitmentService.deleteRecruit(deleteRecruitRequest));
    }

    @Test
    @DisplayName("getRecruitList_??????")
    void getRecruitList_??????() {
        //given
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest("company1", "Korea", "Seoul");
        companyService.createCompany(createCompanyRequest);
        Optional<Company> findCompany = companyRepository.findByCompanyName(createCompanyRequest.getCompanyName());

        CreateRecruitRequest createRecruitRequest1 = CreateRecruitRequest.builder()
                .companyId(findCompany.get().getId())
                .position("Backend")
                .reward(2000000)
                .contents("now we are hiring,,,")
                .techInfo("Python")
                .build();

        CreateRecruitRequest createRecruitRequest2 = CreateRecruitRequest.builder()
                .companyId(findCompany.get().getId())
                .position("Backend")
                .reward(1800000)
                .contents("now we are hiring,,,")
                .techInfo("Java")
                .build();

        recruitmentService.createRecruit(createRecruitRequest1);
        recruitmentService.createRecruit(createRecruitRequest2);

        //when
        List<GetRecruitListResponse> all = recruitmentService.getRecruitList();

        //then
        assertAll(
                () -> assertThat(all.size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("getSearchRecruitList_??????")
    void getSearchRecruitList_??????() {
        //given
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest("company1", "Korea", "Seoul");
        companyService.createCompany(createCompanyRequest);
        Optional<Company> findCompany = companyRepository.findByCompanyName(createCompanyRequest.getCompanyName());

        CreateRecruitRequest createRecruitRequest1 = CreateRecruitRequest.builder()
                .companyId(findCompany.get().getId())
                .position("Backend")
                .reward(2000000)
                .contents("now we are hiring,,,")
                .techInfo("Python")
                .build();

        CreateRecruitRequest createRecruitRequest2 = CreateRecruitRequest.builder()
                .companyId(findCompany.get().getId())
                .position("Backend")
                .reward(1800000)
                .contents("now we are hiring,,,")
                .techInfo("Java")
                .build();

        recruitmentService.createRecruit(createRecruitRequest1);
        recruitmentService.createRecruit(createRecruitRequest2);

        //when
        List<GetSearchRecruitResponse> result = recruitmentService.getSearchRecruitList("Java");

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTechInfo()).isEqualTo("Java");
    }

    @Test
    @DisplayName("getRecruitDetail_??????")
    void getRecruitDetail_??????() {
        //given
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest("company1", "Korea", "Seoul");
        companyService.createCompany(createCompanyRequest);
        Optional<Company> findCompany = companyRepository.findByCompanyName(createCompanyRequest.getCompanyName());

        CreateRecruitRequest createRecruitRequest = CreateRecruitRequest.builder()
                .companyId(findCompany.get().getId())
                .position("Backend")
                .reward(2000000)
                .contents("now we are hiring,,,")
                .techInfo("Python")
                .build();
        CreateRecruitResponse createRecruitResponse = recruitmentService.createRecruit(createRecruitRequest);

        //when
        GetRecruitDetailResponse recruitDetail = recruitmentService.getRecruitDetail(createRecruitResponse.getRecruitId());

        //then
        Optional<Recruitment> recruitment = recruitmentRepository.findById(createRecruitResponse.getRecruitId());
        assertAll(
                () -> assertThat(recruitDetail.getRecruitId()).isEqualTo(recruitment.get().getId()),
                () -> assertThat(recruitDetail.getCompanyName()).isEqualTo(recruitment.get().getCompany().getCompanyName()),
                () -> assertThat(recruitDetail.getCountry()).isEqualTo(recruitment.get().getCompany().getCountry()),
                () -> assertThat(recruitDetail.getRegion()).isEqualTo(recruitment.get().getCompany().getRegion()),
                () -> assertThat(recruitDetail.getPosition()).isEqualTo(recruitment.get().getPosition()),
                () -> assertThat(recruitDetail.getReward()).isEqualTo(recruitment.get().getReward()),
                () -> assertThat(recruitDetail.getContents()).isEqualTo(recruitment.get().getContents()),
                () -> assertThat(recruitDetail.getTechInfo()).isEqualTo(recruitment.get().getTechInfo())
        );
    }

    @Test
    @DisplayName("getRecruitDetail_??????_??????????????????_????????????")
    void getRecruitDetail_??????_??????????????????_????????????() {
        //given

        //when

        //then
        assertThrows(NotFoundException.class, () -> recruitmentService.getRecruitDetail(1L));
    }
}
