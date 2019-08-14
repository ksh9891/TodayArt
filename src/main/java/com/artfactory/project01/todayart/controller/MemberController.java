package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.service.MemberService;
import com.artfactory.project01.todayart.util.MemberDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberDetailServiceImpl memberDetailsService;

    /*
       작성자:  희창
       기능 설명 : 이메일중복체크후 회원가입
      @param Member Entity
      @return Save 된 Member 객체
   */
    @PostMapping(value = "/signUp", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member createMember(@RequestBody Member member) {
        if (memberService.findByEmail(member.getEmail()) == null){
            return memberService.createMember(member);
        } else{
            return null;
        }
    }

    /*
       작성자:  희창
       기능 설명 : 로그인
      @param Member Entity
      @return set 된 멤버 객체
    */
    @PostMapping(value = "/signIn", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public UserDetails signIn(@RequestBody Map<String, String> signIn) {
        String email = signIn.get("email");
        String password = signIn.get("password");
        if(email == null || email.isEmpty()){
            System.out.println("이메일을 입력하세요");
        } else if (password == null || password.isEmpty()){
            System.out.println("비밀번호를 입력하세요");
        }
        Member member = (Member) memberDetailsService.loadUserByUsername(email);
        if(member != null && password.compareTo(member.getPassword()) != 0) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }
            return member;
    }

    /*
       작성자:  희창
       기능 설명 : 전체 회원 조회
      @param X
      @return List<Member> 화 된 Member 객체
    */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Member> retrieveMembers() {
        return memberService.retrieveMembers();
    }

    /*
       작성자:  희창
       기능 설명 : 회원 정보 조회(개인)
      @param int id(PathVariable)
      @return id에 맞는 Member객체
    */
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member retrieveMember(@PathVariable("id") int id) {
        return memberService.retrieveMember(id);
    }

    /*
       작성자:  희창
       기능 설명 : 회원 정보 수정
      @param int id(PathVariable), UpdateMember Model
      @return id,updateMember에 맞게 수정된 Member 객체
    */
    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member updateMember(@PathVariable("id") int id, @RequestBody UpdateMember updateMember) {
        return memberService.updateMember(id, updateMember);
    }

    /*
       작성자:  희창
       기능 설명 : 회원 삭제(탈퇴 여부 체크)
      @param int id(PathVariable), UpdateMember Model
      @return id, updateMember에 맞게 체크된 Member 객체
    */
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member deleteMember(@PathVariable("id") int id,UpdateMember updateMember) {
        return memberService.deleteMember(id, updateMember);
    }

    /*
       작성자:  희창
       기능 설명 : 비밀번호 확인
      @param int id(PathVariable), Map<String, String> passwordMap
      @return 같으면 true, 다르면 false
    */
    @GetMapping(value = "/{id}/password", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public boolean retrieveMemberPassword(@PathVariable("id") int id, @RequestBody Map<String, String> passwordMap) {
        String password = passwordMap.get("password");
        String userPassword = memberService.retrieveMemberPassword(id);
        return password.equals(userPassword) ? true : false;
    }

    /*
       작성자:  희창
       기능 설명 : 닉네임 중복 체크(회원정보 수정에서)
      @param Member entity
      @return 중복이면 true, 중복 아니면 false
    */

    // 닉네임 중복체크 로직 진행해야 함.
    @GetMapping(value = "/nickname", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean checkNickname(@RequestBody Member member) {
        return memberService.findByNickname(member.getNickname()) != null ? true : false;
        // 닉네임 중복체크 로직 완료+
    }

    /*
       작성자:  희창
       기능 설명 : 비밀번호 변경시 현재 비밀번호와 중복 체크(회원정보 수정에서)
      @param int id(PathVariable), Map<String, String> passwordMap
      @return 중복이면 true, 중복 아니면 false
    */
    @GetMapping(value = "/{id}/checkPassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean checkPassword(@PathVariable("id") int id, @RequestBody Map<String, String> passwordMap) {
        String password = passwordMap.get("password");
        String checkPassword = memberService.findById(id).get().getPassword();
        return password.equals(checkPassword) ? true : false;
    }
    // 비밀번호 변경 로직 해야 함.
    // 우편번호 찾기 및 배송지 추가
    // 변경사항 저장 로직 추가
    // react에서 sha256 암호화로 DB에 비밀번호 저장
}
