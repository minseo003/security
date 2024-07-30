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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    private String email;
    private String role;

    @Builder
    public User(Long id, String userId, String password, String email, String role) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void toEntity(UserRequestDTO dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.role = dto.getRole();
    }
}
