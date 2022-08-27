package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.common.exception.ErrorCode;
import assignment.wanted_pre_onboarding.common.exception.model.ConflictException;
import assignment.wanted_pre_onboarding.common.exception.model.NotFoundException;
import assignment.wanted_pre_onboarding.domain.apply.Apply;
import assignment.wanted_pre_onboarding.domain.apply.ApplyRepository;
import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import assignment.wanted_pre_onboarding.domain.recruitment.RecruitmentRepository;
import assignment.wanted_pre_onboarding.domain.user.User;
import assignment.wanted_pre_onboarding.domain.user.UserRepository;
import assignment.wanted_pre_onboarding.controller.apply.dto.request.CreateApplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static assignment.wanted_pre_onboarding.common.exception.ErrorCode.CONFLICT_EXCEPTION;
import static assignment.wanted_pre_onboarding.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final RecruitmentRepository recruitmentRepository;
    private final UserRepository userRepository;
    private final ApplyRepository applyRepository;

    public void createApply(CreateApplyRequest request) {
        Optional<Recruitment> recruitment = recruitmentRepository.findById(request.getRecruitId());
        Optional<User> user = userRepository.findById(request.getUserId());

        if (recruitment.isEmpty()) {
            throw new NotFoundException("존재하지 않는 채용공고입니다.", NOT_FOUND_EXCEPTION);
        }
        if (user.isEmpty()) {
            throw new NotFoundException("존재하지 않는 사용자입니다.", NOT_FOUND_EXCEPTION);
        }

        Optional<Apply> apply = applyRepository.findByUser(user.get());
        if (apply.isPresent()) {
            // 사용자는 한 채용공고에 한번만 지원할 수 있다
            throw new ConflictException("이미 지원한 채용공고 입니다.", CONFLICT_EXCEPTION);
        }

        Apply created = Apply.of(user.get(), recruitment.get());
        applyRepository.save(created);
    }
}
