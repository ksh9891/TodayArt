package com.artfactory.project01.todayart.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/*
 * 작성자 : 상현
 * 용도 : 클라이언트에 데이터 전송 시 Http 상태코드와 역할에 따른 메시지 저장
 */

@Getter
@Setter
public class HttpStatusMessage {
    public HttpStatus statusCode;
    public String statusMessage;
}
