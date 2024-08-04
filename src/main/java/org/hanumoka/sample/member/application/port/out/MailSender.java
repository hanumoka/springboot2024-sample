package org.hanumoka.sample.member.application.port.out;

public interface MailSender {
    void sendMail(String email, String title, String content);
}
