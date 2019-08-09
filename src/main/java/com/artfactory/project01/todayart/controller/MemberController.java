package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.repository.MemberRepository;
import com.artfactory.project01.todayart.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UpdateService updateService;

    @RequestMapping(
            value = "/signup",
            method = RequestMethod.POST, // 요청된 자원 생성
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE
            })
    public Member signup(@RequestBody Member member){
        return memberRepository.save(member);
    }

    @RequestMapping(
            value = "/mypage/{id}",
            method = RequestMethod.GET, // 검색
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE
            })
    public Member profile(@PathVariable("id") int id){
        return memberRepository.findById(id).get();
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.PATCH,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE
            })
    public Member delete(@PathVariable("id") int id, UpdateMember updateMember){
        return updateService.update(id, updateMember);
    }

//    @RequestMapping(value="joinConfirm", method = RequestMethod.GET)
//    public String emailConfirm(Member member, Model model) throws Exception{
//        logger.info(member.getEmail() + ": auth confirmed");
//    }
}
