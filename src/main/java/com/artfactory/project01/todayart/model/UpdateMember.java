package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.service.MemberService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


@Getter
@Setter
public class UpdateMember {

    // 주문자 이름
    private String username;

    // 비밀번호
    private String password;

    // 별명
    private String nickname;

    // 휴대폰 번호
    private String phone;

    // 탈퇴 여부
    private Integer expired;

    // 탈퇴 일자
    private Date expiredDated;

    private Member member;

    @Autowired
    private MemberService memberService;

    public void member(Member member){

        member.getEmail();

        if(this.password != null) { // 입력받은값이 null이 아니면 값 변경
            member.setPassword(password);
        }
        if(this.username != null) {
            member.setUsername(username);
        }
        if(this.nickname != null || memberService.findByNickname(member.getNickname()) == null) {
            member.setNickname(nickname);
        }
        if(this.phone != null) {
            member.setPhone(phone);
        }

        if(this.expired != null) {
            member.setExpired(expired);
        }

        if(this.expiredDated != null) {
            member.setExpiredDated(expiredDated);
        }

        this.member = member; // 저장된 member객체를 UpdateMember클래스의 member에 대입
    }
}
