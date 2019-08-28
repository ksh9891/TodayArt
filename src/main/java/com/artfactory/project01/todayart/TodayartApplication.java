package com.artfactory.project01.todayart;

import com.artfactory.project01.todayart.model.Mail;
import com.artfactory.project01.todayart.service.EmailService;


import com.artfactory.project01.todayart.util.FileStorageProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.TimeZone;

@EnableConfigurationProperties(value = FileStorageProperties.class)
@SpringBootApplication
//public class TodayartApplication implements ApplicationRunner {
 public class TodayartApplication {

//    @Autowired
//    public JavaMailSender emailSender;

    public static void main(String[] args) {
        SpringApplication.run(TodayartApplication.class, args);
    }

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("ksh9891@gmail.com");
//        message.setSubject("테스트 메일입니다.");
//        message.setText("테스트 콘텐츠입니다.");
//
//        emailSender.send(message);
//    }
}
