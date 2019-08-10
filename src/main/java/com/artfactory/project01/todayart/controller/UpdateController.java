package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.UpdateMember;
import com.artfactory.project01.todayart.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController // responsebody가 포함된 컨트롤러(데이터를 처리할때 주로 사용)
public class UpdateController {

    @Autowired // 자동 의존성 주입
    private UpdateService updateService; // updateService 객체 생성

    @RequestMapping(
            value = "/mypage/{id}", //url주소
            method = RequestMethod.PATCH,
            // patch, put 둘다 update지만 patch는 일부 교체
            // put은 전체 교체
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE
                    // json형태로 받아오고, 인코딩 방식은 utf8 사용
            })
    public Member update(@PathVariable("id")int id, @RequestBody UpdateMember updateMember){
        // @PathVariable url에 path부분의 데이터 가져와서 대입
        // @Requestbody 응답받은 값을 객체에 전달
        return updateService.update(id, updateMember); // updateService.update에 id,updateMember를 가지고 리턴
    }
}
