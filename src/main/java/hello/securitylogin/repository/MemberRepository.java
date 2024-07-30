package hello.securitylogin.repository;

import hello.securitylogin.entity.User;
import org.aspectj.lang.annotation.After;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<User,Long> {

}
