package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.common.exception.model.ConflictException;
import assignment.wanted_pre_onboarding.common.exception.model.NotFoundException;
import assignment.wanted_pre_onboarding.controller.apply.dto.request.CreateApplyRequest;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.request.CreateRecruitRequest;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.response.CreateRecruitResponse;
import assignment.wanted_pre_onboarding.domain.apply.Apply;
import assignment.wanted_pre_onboarding.domain.apply.ApplyRepository;
import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.company.CompanyRepository;
import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import assignment.wanted_pre_onboarding.domain.recruitment.RecruitmentRepository;
import assignment.wanted_pre_onboarding.domain.user.User;
import assignment.wanted_pre_onboarding.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private RecruitmentService recruitmentService;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void cleanUp() {
        applyRepository.deleteAllInBatch();
        recruitmentRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        companyRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("createApply_성공")
    void createApply_성공() {
        User user = User.of("user1");
        User findUser = userRepository.save(user);
        Company company = Company.of("company1", "korea", "seoul");
        Company findCompany = companyRepository.save(company);

        CreateRecruitRequest createRecruitRequest = CreateRecruitRequest.builder()
                .companyId(findCompany.getId())
                .position("Backend Junior")
                .reward(1500000)
                .contents("now we are hiring")
                .techInfo("Java")
                .build();
        CreateRecruitResponse createRecruitResponse = recruitmentService.createRecruit(createRecruitRequest);
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(createRecruitResponse.getRecruitId());

        CreateApplyRequest createApplyRequest = CreateApplyRequest.builder()
                .recruitId(findRecruit.get().getId())
                .userId(findUser.getId())
                .build();

        //when
        applyService.createApply(createApplyRequest);

        //then
        Optional<Apply> apply = applyRepository.findByUser(findUser);
        assertAll(
                () -> assertEquals(apply.get().getUser().getId(), findUser.getId()),
                () -> assertEquals(apply.get().getRecruitment().getId(), findRecruit.get().getId())
        );
    }

    @Test
    @DisplayName("createApply_실패_존재하지_않는_채용공고")
    void createApply_실패_존재하지_않는_채용공고() {
        //given
        User user = User.of("user1");
        User findUser = userRepository.save(user);

        CreateApplyRequest createApplyRequest = CreateApplyRequest.builder()
                .recruitId(1L)
                .userId(findUser.getId())
                .build();

        //when

        //then
        assertThrows(NotFoundException.class, () -> applyService.createApply(createApplyRequest));
    }

    @Test
    @DisplayName("createApply_실패_존재하지_않는_사용자")
    void createApply_실패_존재하지_않는_사용자() {
        //given
        Company company = Company.of("company1", "korea", "seoul");
        Company findCompany = companyRepository.save(company);

        CreateRecruitRequest createRecruitRequest = CreateRecruitRequest.builder()
                .companyId(findCompany.getId())
                .position("Backend Junior")
                .reward(1500000)
                .contents("now we are hiring")
                .techInfo("Java")
                .build();
        CreateRecruitResponse createRecruitResponse = recruitmentService.createRecruit(createRecruitRequest);
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(createRecruitResponse.getRecruitId());

        CreateApplyRequest createApplyRequest = CreateApplyRequest.builder()
                .recruitId(findRecruit.get().getId())
                .userId(1L)
                .build();

        //when

        //then
        assertThrows(NotFoundException.class, () -> applyService.createApply(createApplyRequest));
    }

    @Test
    @DisplayName("createApply_실패_중복지원")
    void createApply_실패_중복지원() {
        User user = User.of("user1");
        User findUser = userRepository.save(user);
        Company company = Company.of("company1", "korea", "seoul");
        Company findCompany = companyRepository.save(company);

        CreateRecruitRequest createRecruitRequest = CreateRecruitRequest.builder()
                .companyId(findCompany.getId())
                .position("Backend Junior")
                .reward(1500000)
                .contents("now we are hiring")
                .techInfo("Java")
                .build();
        CreateRecruitResponse createRecruitResponse = recruitmentService.createRecruit(createRecruitRequest);
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(createRecruitResponse.getRecruitId());

        CreateApplyRequest createApplyRequest = CreateApplyRequest.builder()
                .recruitId(findRecruit.get().getId())
                .userId(findUser.getId())
                .build();
        applyService.createApply(createApplyRequest);

        //when

        //then
        assertThrows(ConflictException.class, () -> applyService.createApply(createApplyRequest));
    }
}
