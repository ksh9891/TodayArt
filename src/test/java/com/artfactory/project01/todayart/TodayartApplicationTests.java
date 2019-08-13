package com.artfactory.project01.todayart;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.service.MemberService;
import com.sun.media.jfxmedia.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodayartApplicationTests {

    @Autowired
    private MemberService memberService;
    @Test
    public void contextLoads() throws Exception{
        Date regDate = new Date();
        Member regMember = new Member();
        regMember.setUsername("test1234");
        regMember.setPassword("test1234");
        regMember.setNickname("test1234");
        regMember.setEmail("test1234@bit.co.nz");
        regMember.setRegDated(regDate);

        memberService.createMember(regMember);

        Member member = memberService.retrieveMember(regMember.getMemberId());
        Assert.assertNotNull(member);
        Assert.assertEquals(member.getRegDated(), regDate);
    }

}
