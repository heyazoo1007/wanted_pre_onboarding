package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.company.CompanyRepository;
import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import assignment.wanted_pre_onboarding.domain.recruitment.RecruitmentRepository;
import assignment.wanted_pre_onboarding.web.recruitment.dto.request.*;
import assignment.wanted_pre_onboarding.web.recruitment.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    public CreateRecruitResponse createRecruit(CreateRecruitRequest request) {
        Optional<Company> findCompany = companyRepository.findById(request.getCompanyId());

        Recruitment recruitment = Recruitment.of(findCompany.get(),
                request.getPosition(),
                request.getReward(),
                request.getContents(),
                request.getTechInfo());

        Recruitment created = recruitmentRepository.save(recruitment);

        CreateRecruitResponse response = CreateRecruitResponse.of(created);

        return response;
    }

    public UpdateRecruitResponse updateRecruit(UpdateRecruitRequest request) {

        Optional<Recruitment> findRecruit = recruitmentRepository.findById(request.getId());

        findRecruit.get().setPosition(request.getPosition());
        findRecruit.get().setReward(request.getReward());
        findRecruit.get().setContents(request.getContents());
        findRecruit.get().setTechInfo(request.getTechInfo());

        Recruitment updated = recruitmentRepository.save(findRecruit.get());

        UpdateRecruitResponse response = UpdateRecruitResponse.of(updated);

        return response;
    }

    public String deleteRecruit(DeleteRecruitRequest request) {
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(request.getId());
        String position = findRecruit.get().getPosition();

        recruitmentRepository.delete(findRecruit.get());

        return "해당 " + position + " 채용 공고가 삭제되었습니다.";
    }

    public List<GetRecruitListResponse> getRecruitList() {

        List<Recruitment> all = recruitmentRepository.findAll();

        List<GetRecruitListResponse> recruitList = new ArrayList<>();
        for (Recruitment recruitment : all) {
            Company recruitCompany = recruitment.getCompany();

            GetRecruitListResponse response = GetRecruitListResponse.of(recruitCompany, recruitment);

            recruitList.add(response);
        }

        return recruitList;
    }

    public GetRecruitDetailResponse getRecruitDetail(Long id) {
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(id);
        Company company = findRecruit.get().getCompany();
        Long companyId = company.getId();

        List<Long> companyRecruits = new ArrayList<>();
        List<Recruitment> recruits = recruitmentRepository.findByCompanyId(companyId);
        for (Recruitment recruit : recruits) {
            companyRecruits.add(recruit.getId());
        }

        GetRecruitDetailResponse response = GetRecruitDetailResponse.of(company, findRecruit.get(), companyRecruits);

        return response;
    }

    public List<GetSearchRecruitResponse> getSearchRecruitList(String search) {
        List<Recruitment> all = recruitmentRepository.findAll();

        List<GetSearchRecruitResponse> result = new ArrayList<>();
        for (Recruitment recruitment : all) {
            System.out.println(" hello ");
            if (recruitment.getCompany().getCompanyName().contains(search) || recruitment.getCompany().getCountry().contains(search) || recruitment.getCompany().getRegion().contains(search)) {
                GetSearchRecruitResponse response = GetSearchRecruitResponse.of(recruitment);

                result.add(response);
            }

            if (recruitment.getPosition().contains(search) || recruitment.getContents().contains(search) || recruitment.getTechInfo().contains(search)) {
                GetSearchRecruitResponse response = GetSearchRecruitResponse.of(recruitment);

                result.add(response);
            }
        }
        return result;
    }
}
