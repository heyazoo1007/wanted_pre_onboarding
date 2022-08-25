package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.domain.apply.Apply;
import assignment.wanted_pre_onboarding.domain.apply.ApplyRepository;
import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import assignment.wanted_pre_onboarding.domain.recruitment.RecruitmentRepository;
import assignment.wanted_pre_onboarding.domain.user.User;
import assignment.wanted_pre_onboarding.domain.user.UserRepository;
import assignment.wanted_pre_onboarding.web.apply.dto.request.CreateApplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final RecruitmentRepository recruitmentRepository;
    private final UserRepository userRepository;
    private final ApplyRepository applyRepository;

    public void createApply(CreateApplyRequest request) throws Exception {
        Optional<Recruitment> recruitment = recruitmentRepository.findById(request.getRecruitId());
        Optional<User> user = userRepository.findById(request.getUserId());

        Optional<Apply> apply = applyRepository.findByUser(user.get());
        if (apply.isPresent()) {
            // 사용자는 한 채용공고에 한번만 지원할 수 있다
            throw new Exception();
        }

        Apply created = Apply.of(user.get(), recruitment.get());
        applyRepository.save(created);
    }
}
