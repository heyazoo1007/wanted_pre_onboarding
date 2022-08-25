package assignment.wanted_pre_onboarding.domain.company;

import assignment.wanted_pre_onboarding.domain.recruitment.Recruitment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String country;

    private String region;


    @Builder
    public Company(String companyName, String country, String region) {
        this.companyName = companyName;
        this.country = country;
        this.region = region;
    }

    public static Company of(String companyName, String country, String region) {
        Company company = Company.builder()
                .companyName(companyName)
                .country(country)
                .region(region)
                .build();

        return company;
    }
}
