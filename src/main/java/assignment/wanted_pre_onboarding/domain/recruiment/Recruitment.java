package assignment.wanted_pre_onboarding.domain.recruiment;


import lombok.*;

import javax.persistence.*;

@Table
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_id;

    @Setter
    private String position;

    @Setter
    private Integer reward;

    @Setter
    private String contents;

    @Setter
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
