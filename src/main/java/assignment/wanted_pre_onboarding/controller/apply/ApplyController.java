package assignment.wanted_pre_onboarding.controller.apply;


import assignment.wanted_pre_onboarding.common.dto.ApiResponse;
import assignment.wanted_pre_onboarding.service.ApplyService;
import assignment.wanted_pre_onboarding.controller.apply.dto.request.CreateApplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/create")
    public ApiResponse<String> createApply(@RequestBody CreateApplyRequest request) throws Exception {
        applyService.createApply(request);
        return ApiResponse.SUCCESS;
    }
}
