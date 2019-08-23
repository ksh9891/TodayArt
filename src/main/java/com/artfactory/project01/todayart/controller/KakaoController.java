package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Ordered;
import com.artfactory.project01.todayart.service.KakaoService;
import com.artfactory.project01.todayart.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log
@RestController
public class KakaoController {
    @Autowired
    KakaoService kakaoService;
    @Autowired
    OrderService orderService;

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

    @RequestMapping("/kakaoPaySuccess")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity kakaoPaySuccess(@RequestBody Ordered ordered,
                                          @RequestParam("pg_token") String pg_token, @RequestParam("tid") String tid) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        return kakaoService.kakaoPayInfo(ordered, pg_token, tid);
    }

    @GetMapping("/kakaoPaySuccessFail")
    public String kakaoPaySuccessFail(){
        log.info("kakaoPaySuccessFail................");
        return "kakaoPaySuccessFail";
    }

}
