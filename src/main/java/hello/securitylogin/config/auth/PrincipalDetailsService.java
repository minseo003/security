package hello.securitylogin.config.auth;

import hello.securitylogin.entity.User;
import hello.securitylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 이거 오버라이드 안해주면
     * PrincipalDetail에 user생성자 없이 return new PrincipalDetail()로 리턴
     * 내가 회원가입한 정보의 아이디와 패스워드가 아닌
     * 콘솔창?에 있는걸로 로그인된다
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
                });
        return new PrincipalDetails(principal);  //시큐리티의 세션의 유저정보가 저장됨
    }
    /**
     * 이후 securityConfig로 가서 해쉬로 암호회된 비밀번호 비교
     * 시큐리티가 대신 로그인할때 password를 가로채는데
     * password가 어떻게 암호화되어 회원가입이 되었는지 알아야
     * 로그인할때도 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
     */
}
