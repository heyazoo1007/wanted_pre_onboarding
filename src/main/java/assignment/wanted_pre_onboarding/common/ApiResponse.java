package assignment.wanted_pre_onboarding.common;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    public static final ApiResponse<String> SUCCESS = success(null);

    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data);
    }
}
