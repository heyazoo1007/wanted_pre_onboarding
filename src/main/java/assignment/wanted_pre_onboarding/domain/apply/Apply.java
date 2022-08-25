package assignment.wanted_pre_onboarding.domain.apply;

import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import assignment.wanted_pre_onboarding.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "RECRUITMENT_ID")
    private Recruitment recruitment;

    @Builder
    public Apply(User user, Recruitment recruitment) {
        this.user = user;
        this.recruitment = recruitment;
    }

    public static Apply of(User user, Recruitment recruitment) {
        Apply apply = Apply.builder()
                .user(user)
                .recruitment(recruitment)
                .build();

        return apply;
    }
}
