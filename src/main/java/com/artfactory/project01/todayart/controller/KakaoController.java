package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Ordered;
import com.artfactory.project01.todayart.service.KakaoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@Log
@RestController
public class KakaoController {
    @Autowired
    KakaoService kakaoService;

    /**
     * 작성자 : 국화
     * @param ordered
     * @return ResponseEntity<String>
     *     : 결제페이지 주소 혹은 결제실패 주소 return
     */
    @RequestMapping("/kakaoPay")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity kakaoPay(@RequestBody Ordered ordered) {

        return kakaoService.kakaoPayReady(ordered);

    }

    @GetMapping("/kakaoPaySuccess")
    public ResponseEntity kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Ordered ordered) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        return kakaoService.kakaoPayInfo(ordered, pg_token);

    }

    @GetMapping("/kakaoPaySuccessFail")
    public String kakaoPaySuccessFail(){
        log.info("kakaoPaySuccessFail................");
        return "kakaoPaySuccessFail";
    }

}
