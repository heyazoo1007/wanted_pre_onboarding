package assignment.wanted_pre_onboarding.domain.recruitment;


import assignment.wanted_pre_onboarding.domain.company.Company;
import lombok.*;

import javax.persistence.*;

@Table
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Setter
    private String position;

    @Setter
    private Integer reward;

    @Setter
    private String contents;

    @Setter
    private String techInfo;

    @Builder
    public Recruitment(Company company, String position, Integer reward, String contents, String techInfo) {
        this.company = company;
        this.position = position;
        this.reward = reward;
        this.contents = contents;
        this.techInfo = techInfo;
    }

    public static Recruitment of(Company company, String position, Integer reward, String contents, String techInfo) {
        Recruitment recruitment = Recruitment.builder()
                .company(company)
                .position(position)
                .reward(reward)
                .contents(contents)
                .techInfo(techInfo)
                .build();
        return recruitment;
    }
}
