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
    ON_HOLD;

    public boolean canBeCancelled() {
            return (this == PENDING || this == VALIDATING || this == ON_HOLD);
    }

    public boolean isFinalState() {
            return (this == COMPLETED || this == FAILED || this == REFUNDED || this == CHARGEBACK || this == VOIDED);
    }

    public boolean requiresNotification() {
            return (this == COMPLETED || this == FAILED || this == REFUNDED || this == CHARGEBACK);
    }

    public boolean canRetry() {
            return this == FAILED;
    }

    public String getDescription() {
        switch (this) {
            case PENDING:
                return "Payment initiated, awaiting processing";
            case VALIDATING:
                return "Validating payment details";
            case PROCESSING:
                return "Payment is being processed";
            case COMPLETED:
                return "Payment completed successfully";
            case FAILED:
                return "Payment failed";
            case REFUNDED:
                return "Payment fully refunded";
            case PARTLY_REFUNDED:
                return "Payment partially refunded";
            case CHARGEBACK:
                return "Payment disputed and reversed by bank";
            case VOIDED:
                return "Payment was canceled before completion";
            case ON_HOLD:
                return "Payment is on hold for review";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

}
