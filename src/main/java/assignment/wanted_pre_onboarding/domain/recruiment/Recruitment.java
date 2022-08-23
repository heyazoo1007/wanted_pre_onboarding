package assignment.wanted_pre_onboarding.domain.recruiment;


import lombok.Builder;
import lombok.Getter;
import org.hibernate.id.IdentityGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Entity
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_id;

    private String position;

    private Integer reward;

    private String contents;

    private String techInfo;

    @Builder
    public Recruitment(String position, Integer reward, String contents, String techInfo) {
        this.position = position;
        this.reward = reward;
        this.contents = contents;
        this.techInfo = techInfo;
    }

    public static Recruitment of(String position, Integer reward, String contents, String techInfo) {
        Recruitment recruitment = Recruitment.builder()
                .position(position)
                .reward(reward)
                .contents(contents)
                .techInfo(techInfo)
                .build();
        return recruitment;
    }
}
