package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.common.exception.model.ConflictException;
import assignment.wanted_pre_onboarding.domain.user.User;
import assignment.wanted_pre_onboarding.domain.user.UserRepository;
import assignment.wanted_pre_onboarding.controller.user.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static assignment.wanted_pre_onboarding.common.exception.ErrorCode.CONFLICT_EXCEPTION;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(CreateUserRequest request) {
        String userName = request.getUserName();

        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            throw new ConflictException("중복되는 회원이름입니다.", CONFLICT_EXCEPTION);
        }

        User created = User.of(userName);

        userRepository.save(created);
    }
}
