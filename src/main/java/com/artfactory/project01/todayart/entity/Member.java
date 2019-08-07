package com.artfactory.project01.todayart.entity;

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

    @Column(name="authority")
    private Integer authority;

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

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_role",
            joinColumns = @JoinColumn(name = "m_id"),
            inverseJoinColumns = @JoinColumn(name = "r_id")
    )
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : getRoles()){
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
//            authorities.addAll(role.getPrivileges());
        }
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

    public static Member from(Principal principal) {
        if (principal instanceof OAuth2Authentication) {
        } else if (principal instanceof UsernamePasswordAuthenticationToken) {

        }
        return null;
    }
}
