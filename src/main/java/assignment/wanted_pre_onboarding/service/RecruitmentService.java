package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.domain.recruiment.Recruitment;
import assignment.wanted_pre_onboarding.domain.recruiment.RecruitmentRepository;
import assignment.wanted_pre_onboarding.web.recruitment.dto.request.SaveRecruitRequest;
import assignment.wanted_pre_onboarding.web.recruitment.dto.response.SaveRecruitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public SaveRecruitResponse saveRecruit(SaveRecruitRequest request) {
        Recruitment recruitment = Recruitment.builder()
                .position(request.getPosition())
                .reward(request.getReward())
                .contents(request.getContents())
                .techInfo(request.getTechInfo())
                .build();

        Recruitment created = recruitmentRepository.save(recruitment);

        SaveRecruitResponse response = SaveRecruitResponse.of(created);

        return response;
    }
}
