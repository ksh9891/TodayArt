package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.repository.MemberRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.SqlResultSetMapping;
import java.sql.SQLClientInfoException;
import java.sql.SQLData;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    /*
       작성자:  희창
       기능 설명 : 회원가입
      @param Member Entity
      @return save 된 멤버 객체
    */
    @Transactional
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    public String signIn(String email, String password){
        if(memberRepository.findByEmail(email).equals(email)) {
            if (memberRepository.findByEmail(email).getPassword().equals(password)){
                return "redirect:/";
            } else{
                return "비밀번호가 잘못 되었습니다";
            }
        }else{
            return "이메일이 잘못 되었습니다";
        }
    }

    /*
       작성자:  희창
       기능 설명 : 전체 회원 조회
      @param X
      @return memberRepository.findAll() 메서드
    */
    @Transactional(readOnly = true)
    public List<Member> retrieveMembers() {
        return memberRepository.findAll();
    }

    /*
       작성자:  희창
       기능 설명 : 회원 정보 조회
      @param int id
      @return id에 맞는 Member 객체
    */
    @Transactional(readOnly = true)
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
    public Member deleteMember(int id, UpdateMember updateMember){
        Date expiredDated = new Date();
        Member member = memberRepository.findById(id).get();
        updateMember.setExpired(1);
        updateMember.setExpiredDated(expiredDated);
        updateMember.member(member);
        return memberRepository.save(member);
    }
    @Transactional
    public String retrieveMemberPassword(int id) {
        return memberRepository.findById(id).get().getPassword();
    }

    @Transactional
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    @Transactional
    public Optional<Member> findById(int id){
        return memberRepository.findById(id);
    }

    @Transactional
    public Member updatePassword(int id, String password){
        Member member = memberRepository.findById(id).get();
        member.setPassword(password);
        return memberRepository.save(member);
    }
}
