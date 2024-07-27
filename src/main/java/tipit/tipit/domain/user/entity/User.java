package tipit.tipit.domain.user.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;
    @Column(unique = true)
    private String providerId;
    @Enumerated(EnumType.STRING)
    private ExpertField expertField;

    private String nickname;

    @Builder
    public User(Long id, OAuthProvider oAuthProvider,String providerId, ExpertField expertField, String nickname) {
        this.id = id;
        this.oAuthProvider = oAuthProvider;
        this.providerId = providerId;
        this.expertField = expertField;
        this.nickname = nickname;
    }
}