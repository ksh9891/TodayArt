package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 서비스 선언
public class UpdateService {

    @Autowired
    private MemberRepository memberRepository; //memberRepository 객체 생성

    @Transactional
    public Member update(int id, UpdateMember updateMember) {
        Member member = memberRepository.findById(id).get();// id값으로 찾은 객체를 member에 대입
        updateMember.member(member); // updateMember의 member메서드의 매개변수에 전달
        return memberRepository.save(member); // 수정된 값으로 업데이트 실행
    }
}
