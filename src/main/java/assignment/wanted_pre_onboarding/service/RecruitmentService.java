package assignment.wanted_pre_onboarding.service;

import assignment.wanted_pre_onboarding.common.exception.model.NotFoundException;
import assignment.wanted_pre_onboarding.domain.company.Company;
import assignment.wanted_pre_onboarding.domain.company.CompanyRepository;
import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import assignment.wanted_pre_onboarding.domain.recruitment.RecruitmentRepository;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.request.*;
import assignment.wanted_pre_onboarding.controller.recruitment.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static assignment.wanted_pre_onboarding.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    public CreateRecruitResponse createRecruit(CreateRecruitRequest request) {
        Optional<Company> findCompany = companyRepository.findById(request.getCompanyId());

        if (findCompany.isEmpty()) {
            throw new NotFoundException("회사 등록 후 채용공고를 등록할 수 있습니다.", NOT_FOUND_EXCEPTION);
        }

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
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(request.getRecruitId());

        if (findRecruit.isEmpty()) {
            throw new NotFoundException("등록되지 않은 채용공고입니다.", NOT_FOUND_EXCEPTION);
        }

        findRecruit.get().setPosition(request.getPosition());
        findRecruit.get().setReward(request.getReward());
        findRecruit.get().setContents(request.getContents());
        findRecruit.get().setTechInfo(request.getTechInfo());

        Recruitment updated = recruitmentRepository.save(findRecruit.get());

        UpdateRecruitResponse response = UpdateRecruitResponse.of(updated);

        return response;
    }

    public void deleteRecruit(DeleteRecruitRequest request) {
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(request.getRecruitId());

        if (findRecruit.isEmpty()) {
            throw new NotFoundException("존재하지 않는 채용공고입니다.", NOT_FOUND_EXCEPTION);
        }

        String position = findRecruit.get().getPosition();

        recruitmentRepository.delete(findRecruit.get());
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

    public List<GetSearchRecruitResponse> getSearchRecruitList(String search) {
        List<Recruitment> all = recruitmentRepository.findAll();

        List<GetSearchRecruitResponse> result = new ArrayList<>();
        for (Recruitment recruitment : all) {
            if (recruitment.getCompany().getCompanyName().contains(search) || recruitment.getCompany().getCountry().contains(search) || recruitment.getCompany().getRegion().contains(search)) {
                GetSearchRecruitResponse response = GetSearchRecruitResponse.of(recruitment);

                result.add(response);
            }

            if (recruitment.getPosition().contains(search) || recruitment.getTechInfo().contains(search)) {
                GetSearchRecruitResponse response = GetSearchRecruitResponse.of(recruitment);

                result.add(response);
            }
        }
        return result;
    }

    public GetRecruitDetailResponse getRecruitDetail(Long id) {
        Optional<Recruitment> findRecruit = recruitmentRepository.findById(id);

        if (findRecruit.isEmpty()) {
            throw new NotFoundException("존재하지 않는 채용공고입니다.", NOT_FOUND_EXCEPTION);
        }

        Company company = findRecruit.get().getCompany();
        Long companyId = company.getId();

        List<Long> companyRecruits = new ArrayList<>();
        List<Recruitment> recruits = recruitmentRepository.findByCompanyId(companyId);
        for (Recruitment recruit : recruits) {
            if (!recruit.getId().equals(id)) {
                companyRecruits.add(recruit.getId());
            }
        }

        GetRecruitDetailResponse response = GetRecruitDetailResponse.of(company, findRecruit.get(), companyRecruits);

        return response;
    }
}
