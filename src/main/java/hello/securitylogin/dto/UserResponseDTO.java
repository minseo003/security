package hello.securitylogin.dto;

import hello.securitylogin.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserResponseDTO {
    @NotBlank
    private Long id;
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private String role;
    @Builder
    public UserResponseDTO(User entity) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
