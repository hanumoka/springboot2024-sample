package org.hanumoka.sample.common.domain;

import java.time.ZonedDateTime;

public abstract class BaseDomain {
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    protected BaseDomain() {
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    protected void updateLastModifiedTime() {
        this.updatedAt = ZonedDateTime.now();
    }
}
