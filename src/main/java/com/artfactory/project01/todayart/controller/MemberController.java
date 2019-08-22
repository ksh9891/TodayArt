package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.MemberRegister;
import com.artfactory.project01.todayart.model.HttpStatusMessage;
import com.artfactory.project01.todayart.service.MemberService;
import com.artfactory.project01.todayart.util.MemberDetailServiceImpl;
import com.artfactory.project01.todayart.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberDetailServiceImpl memberDetailsService;

    private boolean isResult;

    /*
      작성자:  희창
      기능 설명 : 비회원인지 확인하고 이메일 중복체크후 회원가입
      @param MemberRegister
      @return Member
   */
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member createMember(@RequestBody MemberRegister memberRegister) {
        if (memberService.findByEmail(memberRegister.getEmail()) == null) {
            return memberService.createMember(memberRegister.setMember());
        } else {
            throw new BadCredentialsException("이미 가입된 이메일입니다");
        }
    }

    /*
       작성자:  희창
       기능 설명 : 로그인
      @param Member
      @return Member
    */
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(path = "/signIn", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public UserDetails signIn(@RequestBody Map<String, String> signMember) {
        String email = signMember.get("email");
        String password = signMember.get("password");

        if(email == null || email.isEmpty() || memberService.findByEmail(email) == null){
            throw new InvalidParameterException("없는 이메일이거나 이메일을 입력하지 않았습니다");
        } else if(password == null || password.isEmpty()){
            throw new InvalidParameterException("비밀번호를 입력하지 않았습니다");
        }

        Member member = (Member) memberDetailsService.loadUserByUsername(email);
        if (member != null && password.compareTo(member.getPassword()) != 0) {
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
    // 관리자 기능
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public List<Member> retrieveMembers() {
//        return memberService.retrieveMembers();
//    }

    /*
       작성자:  희창
       기능 설명 : 회원 정보 조회(개인)
      @param id(PathVariable), passwordMap, principal
      @return id에 맞는 Member객체
    */
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public UserDetails retrieveMember(@PathVariable("id") int id,@RequestBody Map<String, String> passwordMap, Principal principal) {
        if(PrincipalUtil.from(principal).getPassword().equals(passwordMap.get("password"))) {
            return memberService.retrieveMember(id);
        } else {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }
    }

    /*
       작성자:  희창
       기능 설명 : 회원 정보 수정
      @param int id(PathVariable), UpdateMember Model
      @return id,updateMember에 맞게 수정된 Member 객체
    */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @PatchMapping(path = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public UserDetails updateMember(@RequestBody Map<String, String> updateMap,
                                    Principal principal) {

        // JSON받을때 확인용으로 현재 비밀번호 받아서 비교
        if(PrincipalUtil.from(principal).getPassword().equals(updateMap.get("checkPassword"))) {
            return memberService.updateMember(principal, updateMap);
        } else{
            throw new BadCredentialsException("정보가 일치하지 않습니다");
        }
    }
    /*
       작성자:  희창
       기능 설명 : 회원 삭제(탈퇴 여부 체크)
      @param int id(PathVariable), UpdateMember Model
      @return id, updateMember에 맞게 체크된 Member 객체
    */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public UserDetails deleteMember(@RequestBody Map<String, String> passwordMap, Principal principal) {
        if (PrincipalUtil.from(principal).getPassword().equals(passwordMap.get("password"))) {
            return memberService.deleteMember((Member) PrincipalUtil.from(principal));
        } else {
            throw new BadCredentialsException("정보가 일치하지 않습니다");
        }
    }

    /*
       작성자:  희창
       기능 설명 : 비밀번호 확인
      @param int id(PathVariable), Map<String, String> passwordMap
      @return 같으면 true, 다르면 false
    */
    @GetMapping(path = "/checkPassword", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public boolean retrieveMemberPassword(@RequestBody Map<String, String> passwordMap, Principal principal) {
        String password = passwordMap.get("password");
        String userPassword = PrincipalUtil.from(principal).getPassword();
        isResult = password.equals(userPassword) ? true : false;
        return isResult;
    }

    /*
       작성자:  희창
       기능 설명 : 닉네임 중복 체크(회원정보 수정에서)
      @param Member entity
      @return 중복이면 true, 중복 아니면 false
    */

    // 닉네임 중복체크 로직 진행해야 함.
    @GetMapping(path = "/nickname", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean checkNickname(@RequestBody Member member) {
        return memberService.findByNickname(member.getNickname()) != null ? true : false;
        // 닉네임 중복체크 로직 완료+
    }

    /*
       작성자:  희창
       기능 설명 : 중복체크 결과로 비밀번호 변경
      @param int id(PathVariable), Map<String, String> passwordMap
      @return true면 변경될 값 전달
    */
    // 이 메서드가 있어야 하는지 헷갈립니다
//    @PatchMapping(path = "/{id}/updatePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public UserDetails updatePassword(@PathVariable("id") int id, @RequestBody Map<String, String> passwordMap, Principal principal) {
//        String password = passwordMap.get("password");
//        String checkPassword = PrincipalUtil.from(principal).getPassword();
//        if(isResult == true) {
//            return memberService.updatePassword(id, password);
//        } else{
//            return memberService.updatePassword(id, checkPassword);
//        }
//    }

    /*
     * 작성자 : 상현
     * 기능 : 토큰 인증 후 사용자 정보를 얻는다
     */
    @GetMapping(path = "/me", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Member me(Principal principal) {
        return (Member) memberDetailsService.loadUserByUsername(principal.getName());
    }

    /*
     * 작성자 : 상현
     * 기능 : 중복확인
     * 인자 : data, flag
     * 리턴 : HttpStatusMessage 객체
     */
    @GetMapping(path = "/checkEmail", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public HttpStatusMessage checkEmail(@RequestParam String email) {
        HttpStatusMessage httpStatusMessage = new HttpStatusMessage();
        Member member = memberService.findByEmail(email);

        if (member == null) {
            httpStatusMessage.setStatusCode(HttpStatus.OK);
            httpStatusMessage.setStatusMessage("사용 가능 한 이메일 입니다.");
        } else {
            httpStatusMessage.setStatusCode(HttpStatus.CONFLICT);
            httpStatusMessage.setStatusMessage("이미 사용중인 이메일 입니다.");
        }

        return httpStatusMessage;
    }

    /*
     * 작성자 : 상현
     * 기능 : 닉네임 중복확인
     * 인자 : nickname
     * 리턴 : HttpStatusMessage 객체
     */
    @GetMapping(path = "/checkNickname", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public HttpStatusMessage checkNickname(@RequestParam String nickname) {
        HttpStatusMessage httpStatusMessage = new HttpStatusMessage();
        Member member = memberService.findByNickname(nickname);

        if (member == null) {
            httpStatusMessage.setStatusCode(HttpStatus.OK);
            httpStatusMessage.setStatusMessage("사용 가능 한 닉네임 입니다.");
        } else {
            httpStatusMessage.setStatusCode(HttpStatus.CONFLICT);
            httpStatusMessage.setStatusMessage("이미 사용중인 닉네임 입니다.");
        }

        return httpStatusMessage;
    }
}
