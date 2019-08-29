package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.EmailVerify;
import com.artfactory.project01.todayart.repository.EmailVerifyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailVerifyService {

    @Autowired
    EmailVerifyRepository emailVerifyRepository;

    @Transactional
    public EmailVerify createEmailVerify(EmailVerify emailVerify) {
        return emailVerifyRepository.save(emailVerify);
    }
}
