package com.artfactory.project01.todayart;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.repository.MemberRepository;
import com.artfactory.project01.todayart.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class unitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void testQuestionBoard() throws Exception{

//        Member member = new Member();
////
////        member.setUsername("test");
////        member.setPassword("test1234");
////        member.setEmail("test@bit.com");
////    memberRepository.save(member);
        memberService.retrieveMember(3000);

    }
}