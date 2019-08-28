package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberUpdate {
    private String nickname;
    private String realName;
    private String phone;
    private String password;
}
