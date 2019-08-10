package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public List<Member> retrieveMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member retrieveMember(int id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public Member updateMember(int id, UpdateMember updateMember) {
        Member member = memberRepository.findById(id).get();// id값으로 찾은 객체를 member에 대입
        updateMember.member(member); // updateMember의 member메서드의 매개변수에 전달
        return memberRepository.save(member); // 수정된 값으로 업데이트 실행
    }

    @Transactional
    public String retrieveMemberPassword(int id) {
        return memberRepository.findById(id).get().getPassword();
    }

    @Transactional
    public String retrieveMemberEmail(int id) {
        return memberRepository.findById(id).get().getEmail();
    }
}
