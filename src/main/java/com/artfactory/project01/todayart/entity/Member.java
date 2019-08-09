package com.artfactory.project01.todayart.entity;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;
import java.util.*;

@Entity
@Table(name="member")
@DynamicInsert
public class Member implements UserDetails, Serializable {
    @Id
    @Column(name="member_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="nickname")
    private String nickname;

    @Column(name="username")
    private String username;

    @Column(name="reg_dated")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date regDated;

    @Column(name="phone")
    private String Phone;

    @Column(name="role")
    private String role;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_connect_dated")
    private Date lastConnectDated;

    @Column(name="expired")
    private Integer expired;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="expired_dated")
    private Date expiredDated;

    @Column(name="email_checked")
    private Integer emailChecked;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegDated() {
        return regDated;
    }

    public void setRegDated(Date regDated) {
        this.regDated = regDated;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastConnectDated() {
        return lastConnectDated;
    }

    public void setLastConnectDated(Date lastConnectDated) {
        this.lastConnectDated = lastConnectDated;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Date getExpiredDated() {
        return expiredDated;
    }

    public void setExpiredDated(Date expiredDated) {
        this.expiredDated = expiredDated;
    }

    public Integer getEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(Integer emailChecked) {
        this.emailChecked = emailChecked;
    }


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        return authorities;
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
=======
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
>>>>>>> member

}
