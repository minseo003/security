package hello.securitylogin.entity;

import hello.securitylogin.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER")
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Builder
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }


}
