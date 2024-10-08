package org.hanumoka.sample.mock.fake;

import org.hanumoka.sample.common.application.port.out.MailSender;

public class FakeMailSender implements MailSender {

    public String email;
    public String title;
    public String content;

    @Override
    public void sendMail(String email, String title, String content) {
        this.email = email;
        this.title = title;
        this.content = content;
    }
}
