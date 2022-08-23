package assignment.wanted_pre_onboarding.web.recruitment;

import assignment.wanted_pre_onboarding.common.ApiResponse;
import assignment.wanted_pre_onboarding.service.RecruitmentService;
import assignment.wanted_pre_onboarding.web.recruitment.dto.request.SaveRecruitRequest;
import assignment.wanted_pre_onboarding.web.recruitment.dto.request.UpdateRecruitRequest;
import assignment.wanted_pre_onboarding.web.recruitment.dto.response.SaveRecruitResponse;
import assignment.wanted_pre_onboarding.web.recruitment.dto.response.UpdateRecruitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/save")
    public ApiResponse<SaveRecruitResponse> saveRecruit(@RequestBody SaveRecruitRequest request) {
        SaveRecruitResponse response = recruitmentService.saveRecruit(request);
        return ApiResponse.success(response);
    }

    @PatchMapping("/update")
    public ApiResponse<UpdateRecruitResponse> updateRecruit(@RequestBody UpdateRecruitRequest request) {
        UpdateRecruitResponse response = recruitmentService.updateRecruit(request);
        return ApiResponse.success(response);
    }
}
