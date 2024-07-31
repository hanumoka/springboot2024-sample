package org.hanumoka.sample.member.service;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.member.service.port.MailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailSenderService {
    private final MailSender mailSender;

    public void sendMail(String email, String title, String content) {
        mailSender.sendMail(email, title, content);
    }
}
