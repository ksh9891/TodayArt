package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.repository.MemberRepository;
import com.artfactory.project01.todayart.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE
            })
    public Member delete(@PathVariable("id") int id, UpdateMember updateMember){
        Date expiredDated = new Date();
        updateMember.setExpired(1);
        updateMember.setExpiredDated(expiredDated);
        return updateService.update(id, updateMember);
    }
}
