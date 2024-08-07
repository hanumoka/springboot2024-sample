package org.hanumoka.sample.common.application.service;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.common.application.port.out.MailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailSenderService {
    private final MailSender mailSender;

    public void sendMail(String email, String title, String content) {
        mailSender.sendMail(email, title, content);
    }
}
