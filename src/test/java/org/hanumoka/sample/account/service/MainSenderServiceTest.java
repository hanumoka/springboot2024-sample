package org.hanumoka.sample.account.service;

import org.hanumoka.sample.common.application.service.MailSenderService;
import org.hanumoka.sample.mock.fake.FakeMailSender;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MainSenderServiceTest {

    @Test
    public void 이메일과_컨텐츠가_제대로_만들어져서_보내지는지_테스트한다(){

        //given
        FakeMailSender fakeMailSender = new FakeMailSender();
        MailSenderService mailSenderService = new MailSenderService(fakeMailSender);

        //when
        mailSenderService.sendMail("test@test.com", "이메일제목", "test11111111");

        //then
        assertThat(fakeMailSender.email).isEqualTo("test@test.com");
        assertThat(fakeMailSender.title).isEqualTo("이메일제목");
        assertThat(fakeMailSender.content).isEqualTo("test11111111");
    }

}
