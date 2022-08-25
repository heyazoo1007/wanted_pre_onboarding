package assignment.wanted_pre_onboarding.domain.user;

import lombok.*;

import javax.persistence.*;

@Table
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Builder
    public User(String userName) {
        this.userName = userName;
    }

    public static User of(String userName) {
        User user = User.builder()
                .userName(userName)
                .build();

        return user;
    }
}
