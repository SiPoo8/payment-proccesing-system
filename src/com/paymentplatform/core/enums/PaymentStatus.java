package com.paymentplatform.core.enums;

public enum PaymentStatus {
    PENDING,
    VALIDATING,
    PROCESSING,
    COMPLETED,
    FAILED,
    REFUNDED,
    PARTLY_REFUNDED,
    CHARGEBACK,
    VOIDED,
    ON_HOLD,
}
