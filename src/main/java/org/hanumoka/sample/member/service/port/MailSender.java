package org.hanumoka.sample.member.service.port;

public interface MailSender {
    void sendMail(String email, String title, String content);
}
