package com.artfactory.project01.todayart.util;

import com.artfactory.project01.todayart.model.kakao.KakaoAmount;
import com.google.gson.Gson;

import javax.persistence.AttributeConverter;

public class KakaoAmountToJsonConverter implements AttributeConverter<KakaoAmount, String > {

    private final Gson gson;
    public KakaoAmountToJsonConverter(){
        this.gson = new Gson();
    }


    @Override
    public String convertToDatabaseColumn(KakaoAmount kakaoAmount) {
        String json=null;
        try{
            json = gson.toJson(kakaoAmount);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    @Override
    public KakaoAmount convertToEntityAttribute(String string) {
        KakaoAmount kakaoAmount = null;
        try{
            kakaoAmount = gson.fromJson(string, KakaoAmount.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kakaoAmount;
    }
}
