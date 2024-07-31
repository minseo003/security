package hello.securitylogin.config.auth;

import hello.securitylogin.dto.UserRequestDTO;
import hello.securitylogin.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//Authentication 객체에 저장할 수 있는 유일한 타입
public class PrincipalDetails implements UserDetails {

    private User user;

    //일반 시큐리티 로그인
    public PrincipalDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
        collect.add(() -> {
            return user.getRole();});
        return collect;
    }

    public User getUser() {
        return user;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
