package org.hanumoka.sample.common.infrastructure;

import org.hanumoka.sample.common.application.port.in.UuidHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SystemUuidHolder implements UuidHolder {
    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }
}
