package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegister {
    public String email;
    public String nickname;
    public String password;

    public Member setMember() {
        Member member = new Member();
        member.setEmail(getEmail());
        member.setNickname(getNickname());
        member.setPassword(getPassword());

        return member;
    }
}
