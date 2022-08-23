package assignment.wanted_pre_onboarding.web.recruitment;

import assignment.wanted_pre_onboarding.common.ApiResponse;
import assignment.wanted_pre_onboarding.service.RecruitmentService;
import assignment.wanted_pre_onboarding.web.recruitment.dto.request.SaveRecruitRequest;
import assignment.wanted_pre_onboarding.web.recruitment.dto.response.SaveRecruitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/save")
    public ApiResponse<SaveRecruitResponse> save(@RequestBody SaveRecruitRequest request) {
        SaveRecruitResponse response = recruitmentService.saveRecruit(request);
        return ApiResponse.success(response);
    }
}
