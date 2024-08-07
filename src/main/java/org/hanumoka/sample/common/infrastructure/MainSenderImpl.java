package org.hanumoka.sample.common.infrastructure;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.common.application.port.out.MailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MainSenderImpl implements MailSender {
    @Override
    public void sendMail(String email, String title, String content) {
        System.out.println("Send mail to " + email + " with title " + title + " and content " + content);
    }
}
