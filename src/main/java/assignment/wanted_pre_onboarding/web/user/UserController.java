package assignment.wanted_pre_onboarding.web.user;

import assignment.wanted_pre_onboarding.common.ApiResponse;
import assignment.wanted_pre_onboarding.service.UserService;
import assignment.wanted_pre_onboarding.web.user.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ApiResponse<String> createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
        return ApiResponse.SUCCESS;
    }
}
