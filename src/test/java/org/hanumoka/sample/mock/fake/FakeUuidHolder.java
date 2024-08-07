package org.hanumoka.sample.mock.fake;

import lombok.AllArgsConstructor;
import org.hanumoka.sample.common.application.port.in.UuidHolder;

@AllArgsConstructor
public class FakeUuidHolder implements UuidHolder {

    private String uuid;

    @Override
    public String random() {
        return uuid;
    }

}
