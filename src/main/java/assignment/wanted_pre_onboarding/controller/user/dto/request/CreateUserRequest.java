package assignment.wanted_pre_onboarding.controller.user.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserRequest {
    private String userName;

    @Builder
    public CreateUserRequest(String userName) {
        this.userName = userName;
    }

    public static CreateUserRequest of(String userName) {
        CreateUserRequest response = CreateUserRequest.builder()
                .userName(userName)
                .build();

        return response;
    }
}
