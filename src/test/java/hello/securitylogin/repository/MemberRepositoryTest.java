package hello.securitylogin.repository;

import hello.securitylogin.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    UserRepository memberRepository;

    @Test
    void findByID() {
        User user1 = User.builder()
                .username("member1")
                .password("1234")
                .email("min")
                .role("admin")
                .build();

        User user2 = User.builder()
                .username("member2")
                .password("1234")
                .email("min1")
                .role("user")
                .build();
        User save1 = memberRepository.save(user1);
        User save2 = memberRepository.save(user2);

        User findMember1 = memberRepository.findById(save1.getId()).get();
        User findMember2 = memberRepository.findById(save2.getId()).get();
        assertThat(findMember1.getPassword()).isEqualTo("1234");
        assertThat(findMember2.getRole()).isEqualTo("user");
    }


    @Test
    void findAll() {
        User user1 = User.builder()
                .username("member1")
                .password("1234")
                .email("min")
                .role("admin")
                .build();

        User user2 = User.builder()
                .username("member2")
                .password("1234")
                .email("min1")
                .role("user")
                .build();
        User save1 = memberRepository.save(user1);
        User save2 = memberRepository.save(user2);

        List<User> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void delete() {
        User user1 = User.builder()
                .username("member1")
                .password("1234")
                .email("min")
                .role("admin")
                .build();

        User user2 = User.builder()
                .username("member2")
                .password("1234")
                .email("min1")
                .role("user")
                .build();
        User save1 = memberRepository.save(user1);
        User save2 = memberRepository.save(user2);

        memberRepository.deleteById(1L);
        List<User> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
        memberRepository.deleteById(2L);
        List<User> all2 = memberRepository.findAll();
        assertThat(all2.size()).isEqualTo(0);
    }

}