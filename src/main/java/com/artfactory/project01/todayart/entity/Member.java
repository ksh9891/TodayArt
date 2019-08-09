package com.artfactory.project01.todayart.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity //entity 선언
@Table(name="member") // member테이블 선언
@Data
@DynamicInsert // 입력된 데이터가 있는것만 insert 실행
@DynamicUpdate // 수정된 데이터만 update문 실행
public class Member implements Serializable {
    @Id //pk값
    @Column(name = "member_id", updatable = false, nullable = false)
    // name  = 컬럼 이름
    // updatable = 수정 가능 여부
    // nullable = null값 허용 여부
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    // 식별자 할당 전략
    // IDENTITY = db에 위임
    // 회원 고유번호
    private Integer id;

    @Column(name = "username", nullable = false)
    // 회원 이름
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    // 회원 이메일(로그인시 ID)
    private String email;

    @Column(name = "password", nullable = false)
    // 회원 비밀번호
    private String password;

    @Column(name = "nickname", nullable = false)
    // 회원 별명(사이트내)
    private String nickname;

    @Column(name = "phone")
    // 회원 전화번호
    private String phone;

    @Column(name="authority", nullable = false)
    // 회원 구분
    private Integer authority;

    @Column(name="email_checked", nullable = false)
    // 이메일 인증
    private Integer emailChecked;

    @Column(name="expired", nullable = false)
    // 인증 만료
    private Integer expired;

    @Column(name="expired_dated")
    // 인증 만료 시간
    private Date expiredDated;

    @Column(name="last_connect_dated")
    private Date lastConnectDated;

    @Column(name="reg_dated")
    private Date regDated;

}
