package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Member retrieveMember(int id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public Member updateMember(int id, Map<String, String> updateMap) {
        Member member = memberRepository.findById(id).get();// id값으로 찾은 객체를 member에 대입
        member.getEmail();
        if(updateMap.get("password") != null &&
                updateMap.get("password").equals(member.getPassword())) { // 입력받은값이 null이 아니면 값 변경
            member.setPassword(updateMap.get("password"));
        }
        if(updateMap.get("username") != null) {
            member.setUsername(updateMap.get("username"));
        }
        if(updateMap.get("nickname") != null && findByNickname(member.getNickname()) == null) {
            member.setNickname(updateMap.get("nickname"));
        }
        if(updateMap.get("phone")!= null) {
            member.setPhone(updateMap.get("phone"));
        }
        return memberRepository.save(member); // 수정된 값으로 업데이트 실행
    }

    @Transactional
    public Member deleteMember(Member member){
        Date expiredDated = new Date();
        member.setExpired(1);
        member.setExpiredDated(expiredDated);
        return memberRepository.save(member);
    }

    @Transactional
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public Member findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    public Member updatePassword(int id,String password){
        Member member = memberRepository.findById(id).get();
        member.setPassword(password);
        return memberRepository.save(member);
    }
}
