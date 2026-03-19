package com.paymentplatform.core.enums;

import java.util.Arrays;
import java.util.List;

public enum PaymentType {
    CREDIT_CARD("Credit Card", 2.5),
    DEBIT_CARD("Debit Card", 1.5),
    PREPAID_CARD("Prepaid Card", 2.0),
    WIRE_TRANSFER("Wire Transfer", 25.0),
    ACH("ACH Transfer", 0.5),
    PAYPAL("PayPal", 3.5),
    APPLE_PAY("Apple Pay", 2.0),
    GOOGLE_PAY("Google Pay", 2.0),
    BANK_TRANSFER("Bank Transfer", 1.0);

    private final String displayName;
    private final double defaultFeePercentage;

    PaymentType(String displayName, double defaultFeePercentage) {
        this.displayName = displayName;
        this.defaultFeePercentage = defaultFeePercentage;
    }

    public boolean requiresCardDetails() {
        return this == CREDIT_CARD || this == DEBIT_CARD || this == PREPAID_CARD;
    }

    public boolean requiresBankAccount() {
        return this == WIRE_TRANSFER || this == ACH || this == BANK_TRANSFER;
    }

    public boolean requiresWalletDetails() {
        return this == PAYPAL || this == APPLE_PAY || this == GOOGLE_PAY;
    }
    public boolean isCardBased() {
        return requiresCardDetails();
    }
    public boolean isBankBased() {
        return requiresBankAccount();
    }
    public boolean isWalletBased() {
        return requiresWalletDetails();
    }

    public static PaymentType fromDisplayName(String displayName) {
        for (PaymentType type : PaymentType.values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        return null;
    }
    public static List<PaymentType> getCardBasedTypes() {
        return Arrays.stream(PaymentType.values())
                .filter(PaymentType::isCardBased)
                .toList();
    }
    public static List<PaymentType> getBankBasedTypes() {
        return Arrays.stream(PaymentType.values())
                .filter(PaymentType::isBankBased)
                .toList();
    }


    public String getDisplayName() {
        return displayName;
    }

    public double getDefaultFeePercentage() {
        return defaultFeePercentage;
    }
}
