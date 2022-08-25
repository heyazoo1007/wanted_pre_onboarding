package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.domain.user.User;
import assignment.wanted_pre_onboarding.domain.user.UserRepository;
import assignment.wanted_pre_onboarding.web.user.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(CreateUserRequest request) {
        User created = User.of(request.getUserName());

        userRepository.save(created);
    }
}
