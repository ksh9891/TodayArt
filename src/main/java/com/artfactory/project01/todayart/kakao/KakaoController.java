package com.artfactory.project01.todayart.kakao;

import com.artfactory.project01.todayart.entity.Ordered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class KakaoController {

    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(Ordered ordered) {
        log.info("kakaoPay post............................................");

        return "redirect:"+kakaopay.kakaoPayReady(ordered);

    }

    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        try {
            model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
            return "kakaoPaySuccess";
        }
        catch(Exception e){
            return "kakaoPaySuccessFail";
        }
    }

    @GetMapping("/kakaoPaySuccessFail")
    public String kakaoPaySuccessFail(){
        log.info("kakaoPaySuccessFail................");
        return "kakaoPaySuccessFail";
    }



}