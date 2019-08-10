package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Member;
import lombok.Data;

import java.util.Date;

@Data
//클래스안의 모든 private 필드에 대해 @Getter와 @Setter를 적용하여 세터/게터를 만들어주고
//클래스내에 @ToString 과 @EqualsAndHashCode를 적용시켜 메소드를 오버라이드 해주며
//@RequiredArgsConstructor를 지정해 준다.
public class UpdateMember {

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private Integer expired;

    private Date expiredDated;

    private Member member;

    public void member(Member member){
        if(this.password != null) { // 입력받은값이 null이 아니면 값 변경
            member.setPassword(password);
        }
        if(this.username != null) {
            member.setUsername(username);
        }
        if(this.nickname != null) {
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
