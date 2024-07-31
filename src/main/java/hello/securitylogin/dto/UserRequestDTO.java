package hello.securitylogin.dto;

import hello.securitylogin.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private String role;

    @Builder
    public UserRequestDTO(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User toEntity() {
        User user = User.builder()
                .username(this.username)
                .password(this.password)
                .role(this.role)
                .build();
        return user;
    }
}
