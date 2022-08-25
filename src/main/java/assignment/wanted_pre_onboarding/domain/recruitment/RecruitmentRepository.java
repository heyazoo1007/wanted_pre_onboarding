package assignment.wanted_pre_onboarding.domain.recruitment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findByCompanyId(Long id);
}
