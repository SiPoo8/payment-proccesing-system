package com.paymentplatform.core.enums;

;
public enum TransactionStatus {
    APPROVED,
    DECLINED,
    PENDING,
    ERROR,
    TIMEOUT,
    CANCELLED;


    public boolean isSuccessful() {
        return this == APPROVED;
    }

    public boolean canRetry() {
        return this == DECLINED || this == ERROR || this == TIMEOUT;
    }

    public boolean isFinalState() {
        return this == APPROVED || this == DECLINED || this == CANCELLED;
    }


    public String getMessage() {
        switch (this) {
            case APPROVED:
                return "Transaction approved";
            case DECLINED:
                return "Transaction declined";
            case PENDING:
                return "Transaction pending";
            case ERROR:
                return "Transaction error occurred";
            case TIMEOUT:
                return "Transaction timed out";
            case CANCELLED:
                return "Transaction cancelled";
            default:
                throw new IllegalStateException("Unexpected status: " + this);
        }
    }

    public boolean requiresReconciliation() {
        return this == PENDING || this == ERROR || this == TIMEOUT;
    }


    public PaymentStatus toPaymentStatus() {
        switch (this) {
            case APPROVED:
                return PaymentStatus.COMPLETED;
            case DECLINED:
            case ERROR:
            case TIMEOUT:
                return PaymentStatus.FAILED;
            case PENDING:
                return PaymentStatus.PENDING;
            case CANCELLED:
                return PaymentStatus.VOIDED;
            default:
                throw new IllegalStateException("Unexpected TransactionStatus: " + this);
        }
    }
}