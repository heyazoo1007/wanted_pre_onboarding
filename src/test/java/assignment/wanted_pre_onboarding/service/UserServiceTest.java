package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.common.exception.model.ConflictException;
import assignment.wanted_pre_onboarding.controller.user.dto.request.CreateUserRequest;
import assignment.wanted_pre_onboarding.domain.user.User;
import assignment.wanted_pre_onboarding.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void clearUp() {
        userRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("사용자_생성_성공")
    void 사용자_생성_성공() {
        //given
        CreateUserRequest request = CreateUserRequest.of("user1");

        userService.createUser(request);

        //when
        Optional<User> findUser = userRepository.findByUserName(request.getUserName());

        //then
        assertThat(findUser.get().getUserName()).isEqualTo(request.getUserName());
    }

    @Test
    @DisplayName("중복된_이름_사용하는_경우")
    void 중복된_이름_사용하는_경우() {
        //given
        CreateUserRequest request1 = CreateUserRequest.of("user1");
        CreateUserRequest request2 = CreateUserRequest.of("user1");

        userService.createUser(request1);

        //then
        assertThrows(ConflictException.class, () -> userService.createUser(request2));
    }
}
