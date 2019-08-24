package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Artist;
import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.repository.ArtistRepository;
import com.artfactory.project01.todayart.repository.MemberRepository;
import com.artfactory.project01.todayart.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Transactional
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member retrieveMember(int id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public Member updateMember(Principal principal, Map<String, String> updateMap) {
        Member member = (Member) PrincipalUtil.from(principal);

        if(updateMap.get("password") != null &&
                (updateMap.get("password")!= (member.getPassword()))) { // 입력받은값이 null이 아니면 값 변경

            member.setPassword(updateMap.get("password"));
        }
        if (updateMap.get("realname") != null) {
            member.setRealName(updateMap.get("realname"));
        }
        if (updateMap.get("nickname") != null && findByNickname(member.getNickname()) == null) {
            member.setNickname(updateMap.get("nickname"));
        }
        if (updateMap.get("phone") != null) {
            member.setPhone(updateMap.get("phone"));
        }
        return memberRepository.save(member); // 수정된 값으로 업데이트 실행
    }


    @Transactional
    public Member deleteMember(Member member){
        Date expiredDated = new Date();
        member.setExpired("y");
        member.setExpiredDated(expiredDated);
        return memberRepository.save(member);
    }

    @Transactional
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    @Transactional
    public Member findByEmail(String email){
        return memberRepository.findByEmail(email);
    }
}
