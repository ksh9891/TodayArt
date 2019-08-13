package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member createMember(@RequestBody Member member){
        return memberService.createMember(member);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Member> retrieveMembers() {
        return memberService.retrieveMembers();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member retrieveMember(@PathVariable("id") int id) {
        return memberService.retrieveMember(id);
    }

    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member updateMember(@PathVariable("id") int id, @RequestBody UpdateMember updateMember) {
        return memberService.updateMember(id, updateMember);
    }

    @GetMapping(value = "/{id}/password")
    public boolean retrieveMemberPassword(@PathVariable("id") int id, @RequestBody Map<String, String> passwordMap) {
        String password = passwordMap.get("password");
        String userPassword = memberService.retrieveMemberPassword(id);
        return password.equals(userPassword) ? true : false;
    }

    // 닉네임 중복체크 로직 진행해야 함.
    @GetMapping(value = "/{id}/change/email", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean changeMemberEmail(@PathVariable("id") int id, @RequestBody Map<String, String> emailMap) {
        String email = emailMap.get("email");
        String userEmail = memberService.retrieveMemberEmail(id);
        // 닉네임 중복체크 로직 완료
        // 닉네임 업데이트 로직 추가부터 시작

        return email.equals(userEmail) ? true : false;
    }

    // 비밀번호 변경 로직 해야 함.
    // 우편번호 찾기 및 배송지 추가
    // 변경사항 저장 로직 추가
    // react에서 sha256 암호화로 DB에 비밀번호 저장

//    @DeleteMapping(value = "/members/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public Member deleteMember(@PathVariable("id") int id, UpdateMember updateMember){
//        return updateService.update(id, updateMember);
//    }
}
