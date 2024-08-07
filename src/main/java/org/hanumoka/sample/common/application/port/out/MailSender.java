package org.hanumoka.sample.common.application.port.out;

public interface MailSender {
    void sendMail(String email, String title, String content);
}
