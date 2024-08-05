package org.hanumoka.sample.common.application.out;

public interface MailSender {
    void sendMail(String email, String title, String content);
}
