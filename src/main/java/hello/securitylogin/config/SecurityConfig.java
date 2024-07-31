package hello.securitylogin.config;

import hello.securitylogin.config.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  //빈 등록
@EnableWebSecurity  //필터체인 관리 시작 어노테이션
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)  //deprecated됨. EnableMethodSecurity를 대신 사용
@EnableMethodSecurity(securedEnabled = true,prePostEnabled = true)  //@Secured ,@PreAuthorize,@PostAuthorize 애노테이션 사용가능 여부에 대한 속성 controller에서 사용
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalDetailsService principalDetailsService;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();  //비밀번호를 암호화해줌
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) //개발시 사용 csrf토큰 비활성화
         // .csrf(csrf -> csrf  //운영, 배포시 사용
                // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                .authorizeHttpRequests(authorize ->authorize
                        .requestMatchers("/user/**").authenticated()  //user라는 url로 들어오면 인증이 필요
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")  //manager으로 들어오는 MANAGER 인증 또는 ADMIN 인증이 필요하다는 뜻이다.
                        .requestMatchers("/admin/**").hasRole("ADMIN") // admin으로 들어오면 ADMIN 권한이 있는 사람만 들어올 수 있음
                        .anyRequest().permitAll()  //그리고 나머지 url은 전부 권한 허용 회원가입되야함

                );
        //프론트
        //권한이 필요한 페이지는 모두 /login페이지로 이동할 수 있도록 함, 주석풀면 정상동작
        http.formLogin(form -> form
                .loginPage("/login") //form을 통한 로그인 활성화, 사용자가 Login이 필요한 페이지에 접근시 사용자에게 보여줄 formPage를 띄워줄 handler의 url지정
                .loginProcessingUrl("/loginProc")  //해당주소로 요청이 오면 시큐리티가 낚아채서 대신 로그인 진행 ,컨트롤러에 로그인url만들지 않아도 됨
                .defaultSuccessUrl("/"));  //성공하면 홈화면


        return http.build();
    }

}
