package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.domain.recruiment.Recruitment;
import assignment.wanted_pre_onboarding.domain.recruiment.RecruitmentRepository;
import assignment.wanted_pre_onboarding.web.recruitment.dto.request.SaveRecruitRequest;
import assignment.wanted_pre_onboarding.web.recruitment.dto.request.UpdateRecruitRequest;
import assignment.wanted_pre_onboarding.web.recruitment.dto.response.SaveRecruitResponse;
import assignment.wanted_pre_onboarding.web.recruitment.dto.response.UpdateRecruitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public UpdateRecruitResponse updateRecruit(UpdateRecruitRequest request) {

        Optional<Recruitment> findRecruit = recruitmentRepository.findById(request.getId());

        findRecruit.get().setPosition(request.getPosition());
        findRecruit.get().setReward(request.getReward());
        findRecruit.get().setContents(request.getContents());
        findRecruit.get().setTechInfo(request.getTechInfo());

        Recruitment updated = recruitmentRepository.save(findRecruit.get());

        UpdateRecruitResponse response = UpdateRecruitResponse.of(updated);

        return response;
    }
}
