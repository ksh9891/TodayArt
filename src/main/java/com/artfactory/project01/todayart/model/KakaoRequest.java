package com.artfactory.project01.todayart.model;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoRequest {
    private String tid, next_redirect_pc_url;
    private Date created_at;
}
