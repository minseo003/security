package hello.securitylogin.controller;

import hello.securitylogin.config.auth.PrincipalDetails;
import hello.securitylogin.dto.UserRequestDTO;
import hello.securitylogin.entity.User;
import hello.securitylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication) {
        log.info("/test/login ==========");
        log.info("authentication : " + authentication.getPrincipal());
        return "세션정보확인하기";
    }
    @GetMapping("/")
    public @ResponseBody String index() {
        return "인덱스 페이지 입니다";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails details) {
        Iterator<? extends GrantedAuthority> iter = details.getAuthorities().iterator();
        while (iter.hasNext()) {
            GrantedAuthority auth = iter.next();
            log.info("auth.Authority = {}", auth);
        }
        return "유저 페이지입니다.";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "어드민 페이지 입니다";
    }

    @Secured("MANAGER")
    @ResponseBody
    @GetMapping("/manager")
    public String manager() {
        return "매니저 페이지 입니다";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProc(User user) {
        log.info("회원가입 진행 : " + user);
        String password = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encPassword);
        user.setRole("ROLE");
        userRepository.save(user);
        return "redirect:/";
    }
}
