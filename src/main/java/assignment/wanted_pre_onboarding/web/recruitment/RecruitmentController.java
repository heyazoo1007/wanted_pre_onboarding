package assignment.wanted_pre_onboarding.web.recruitment;

import assignment.wanted_pre_onboarding.common.ApiResponse;
import assignment.wanted_pre_onboarding.service.RecruitmentService;
import assignment.wanted_pre_onboarding.web.recruitment.dto.request.*;
import assignment.wanted_pre_onboarding.web.recruitment.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/create")
    public ApiResponse<CreateRecruitResponse> createRecruit(@RequestBody CreateRecruitRequest request) {
        CreateRecruitResponse response = recruitmentService.createRecruit(request);
        return ApiResponse.success(response);
    }

    @PatchMapping("/update")
    public ApiResponse<UpdateRecruitResponse> updateRecruit(@RequestBody UpdateRecruitRequest request) {
        UpdateRecruitResponse response = recruitmentService.updateRecruit(request);
        return ApiResponse.success(response);
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteRecruit(@RequestBody DeleteRecruitRequest request) {
        String response = recruitmentService.deleteRecruit(request);
        return ApiResponse.success(response);
    }

    //채용공고 목록 버튼 누르면 나오는 로직, 아직 로그인 기능이 없으므로 사용자관련 로직이 없다
    @GetMapping("/list")
    public ApiResponse<List<GetRecruitListResponse>> getRecruitList() {
        List<GetRecruitListResponse> responses = recruitmentService.getRecruitList();
        return ApiResponse.success(responses);
    }

    @GetMapping("/detail/{id}")
    public ApiResponse<GetRecruitDetailResponse> getRecruitDetail(@PathVariable Long id) {
        GetRecruitDetailResponse response = recruitmentService.getRecruitDetail(id);
        return ApiResponse.success(response);
    }

    @GetMapping("/search")
    public ApiResponse<List<GetSearchRecruitResponse>> getSearchRecruitList(@RequestParam String search) {
        List<GetSearchRecruitResponse> response = recruitmentService.getSearchRecruitList(search);
        return ApiResponse.success(response);
    }
}
