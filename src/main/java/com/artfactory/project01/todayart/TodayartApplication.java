package com.artfactory.project01.todayart;

import com.artfactory.project01.todayart.model.Mail;
import com.artfactory.project01.todayart.service.EmailService;
import com.artfactory.project01.todayart.util.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.TimeZone;

@EnableConfigurationProperties(value = FileStorageProperties.class)
@SpringBootApplication
//public class TodayartApplication implements ApplicationRunner {
public class TodayartApplication {

    @Autowired
    private EmailService emailService;

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+9:00"));
    }

    public static void main(String[] args) {
        SpringApplication.run(TodayartApplication.class, args);
    }

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Mail mail = new Mail();
//
//        mail.setFrom("bit.todayart@gmail.com");
//        mail.setTo("zxcvbnm1436@naver.com");
//        mail.setSubject("테스트 메일입니다.");
//        mail.setContent("테스트 콘텐츠입니다.");
//
//        emailService.sendSimpleMessage(mail);
//    }
}
