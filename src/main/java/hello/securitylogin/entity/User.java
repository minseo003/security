package hello.securitylogin.entity;

import hello.securitylogin.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER")
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends  BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String role;
    private String provider;
    private String providerId;

    @Builder
    public User(String username, String password, String email, String role, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
