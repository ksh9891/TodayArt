package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /*
       작성자:  희창
       기능 설명 : 회원가입
      @param Member Entity
      @return Save 된 Member 객체
   */
    @PostMapping(value = "/signup", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member createMember(@RequestBody Member member){
        return memberService.createMember(member);
    }

    /*
       작성자:  희창
       기능 설명 : 회원가입
      @param Member Entity
      @return set 된 멤버 객체
    */
    @PostMapping(value = "/signIn", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String signIn(@RequestBody Map<String, String> signIn) {
        String email = signIn.get("email");
        String password = signIn.get("password");
        return memberService.signIn(email, password);

    }

    /*
       작성자:  희창
       기능 설명 : 전체 회원 조회
      @param X
      @return memberService.retrieveMembers() 메서드
    */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Member> retrieveMembers() {
        return memberService.retrieveMembers();
    }

    /*
       작성자:  희창
       기능 설명 : 전체 회원 조회
      @param int id(PathVariable)
      @return id정보 전달
    */
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member retrieveMember(@PathVariable("id") int id) {
        return memberService.retrieveMember(id);
    }

    /*
       작성자:  희창
       기능 설명 : 회원 정보 수정
      @param int id(PathVariable), UpdateMember Model
      @return id정보 전달
    */
    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member updateMember(@PathVariable("id") int id, @RequestBody UpdateMember updateMember) {
        return memberService.updateMember(id, updateMember);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member deleteMember(@PathVariable("id") int id,UpdateMember updateMember) {
        return memberService.deleteMember(id, updateMember);
    }
    @GetMapping(value = "/{id}/password", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public boolean retrieveMemberPassword(@PathVariable("id") int id, @RequestBody Map<String, String> passwordMap) {
        String password = passwordMap.get("password");
        String userPassword = memberService.retrieveMemberPassword(id);
        return password.equals(userPassword) ? true : false;
    }

    // 닉네임 중복체크 로직 진행해야 함.
    @GetMapping(value = "/nickname", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void checkNickname(@RequestBody Member member){
            if (memberService.findByNickname(member.getNickname()) != null) {
                System.out.println("중복된 닉네임입니다");
            } else {
                System.out.println("사용 가능한 닉네임입니다");
            }
        // 닉네임 중복체크 로직 완료+
        // 닉네임 업데이트 로직 추가부터 시작
    }

    @GetMapping(value = "/{id}/checkPassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void checkPassword(@PathVariable("id") int id, @RequestBody Map<String, String> passwordMap) {
        String password = passwordMap.get("password");
        String checkPassword = memberService.findById(id).get().getPassword();
        if(password.equals(checkPassword)){
            System.out.println("비밀번호가 같습니다. 다른 비밀번호를 입력하세요");
        } else{
            System.out.println("이 비밀번호로 변경하시겠습니까?");
            memberService.updatePassword(id,password);
        }
    }
    // 비밀번호 변경 로직 해야 함.
    // 우편번호 찾기 및 배송지 추가
    // 변경사항 저장 로직 추가
    // react에서 sha256 암호화로 DB에 비밀번호 저장
}
