package com.artfactory.project01.todayart.util;

import com.artfactory.project01.todayart.model.kakao.KakaoCard;
import com.google.gson.Gson;

import javax.persistence.AttributeConverter;

public class KakaoCardToStringConverter implements AttributeConverter<KakaoCard, String> {

    private final Gson gson;
    public KakaoCardToStringConverter(){
        this.gson = new Gson();
    }


    @Override
    public String convertToDatabaseColumn(KakaoCard kakaoCard) {
        String json=null;
        try{
            json = gson.toJson(kakaoCard);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    @Override
    public KakaoCard convertToEntityAttribute(String string) {
        KakaoCard kakaoCard = null;
        try{
            kakaoCard = gson.fromJson(string, KakaoCard.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kakaoCard;
    }
}
