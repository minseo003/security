package hello.securitylogin.entity;

import hello.securitylogin.domain.Address;
import hello.securitylogin.domain.Nation;
import hello.securitylogin.domain.Sex;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userInfo_id")
    private Long id;
    private Sex sex;
    private Nation nation;
    private String age;
    @Embedded
    private Address address;

}
