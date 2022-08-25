package assignment.wanted_pre_onboarding.domain.apply;

import assignment.wanted_pre_onboarding.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    Optional<Apply> findByUser(User user);
}
