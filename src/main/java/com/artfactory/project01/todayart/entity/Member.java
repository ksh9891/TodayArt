package com.artfactory.project01.todayart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
}