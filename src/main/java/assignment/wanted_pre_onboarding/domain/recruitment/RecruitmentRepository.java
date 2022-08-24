package assignment.wanted_pre_onboarding.domain.recruitment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    public List<Recruitment> findByCompanyId(Long id);
}
