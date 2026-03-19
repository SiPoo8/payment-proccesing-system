package com.paymentplatform.core.enums;

public enum NotificationType {
    EMAIL,
    SMS,
    PUSH,
    WEBHOOK,
    SLACK,
    DASHBOARD;


    public boolean isRealTime() {
        return this == SMS || this == PUSH || this == WEBHOOK;
    }

    public boolean isBatchable() {
        return this == EMAIL || this == DASHBOARD;
    }


    public String getChannelName() {
        return this.name().toLowerCase();
    }
}