package com.artfactory.project01.todayart.model.kakao;

import lombok.Data;

@Data
public class KakaoAmount {
    private Integer total, tax_free, vat, point, discount;

}