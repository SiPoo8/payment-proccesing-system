package com.paymentplatform.core.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Enum representing major credit/debit card types with regex patterns for detection.
 */
public enum CardType {

    VISA("^4[0-9]{12}(?:[0-9]{3})?$", 3, Arrays.asList(13, 16, 19)),

    MASTERCARD("^(5[1-5][0-9]{14}|2(2[2-9][0-9]{12}|[3-6][0-9]{13}|7[01][0-9]{12}|720[0-9]{12}))$",
            3, Arrays.asList(16)),

    AMEX("^3[47][0-9]{13}$", 4, Arrays.asList(15)),

    DISCOVER("^6(?:011|5[0-9]{2})[0-9]{12}$", 3, Arrays.asList(16)),

    JCB("^(?:2131|1800|35[0-9]{3})[0-9]{11}$", 3, Arrays.asList(16)),

    UNIONPAY("^62[0-9]{14,17}$", 3, Arrays.asList(16, 17, 18, 19)),

    DINERS("^3(?:0[0-5]|[68][0-9])[0-9]{11}$", 3, Arrays.asList(14));

    private final String regex;
    private final int cvvLength;
    private final List<Integer> validLengths;

    /**
     * Constructor
     */
    CardType(String regex, int cvvLength, List<Integer> validLengths) {
        this.regex = regex;
        this.cvvLength = cvvLength;
        this.validLengths = validLengths;
    }

    /**
     * Detects card type from card number using regex patterns
     * @param cardNumber Raw card number (may contain spaces/dashes)
     * @return Detected CardType
     * @throws IllegalArgumentException if card type cannot be determined
     */
    public static CardType detectFromNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Card number cannot be null or empty");
        }

        // Remove all non-digit characters
        String cleaned = cardNumber.replaceAll("[^0-9]", "");

        if (cleaned.isEmpty()) {
            throw new IllegalArgumentException("Card number contains no digits");
        }

        // Test each card type's regex
        for (CardType type : CardType.values()) {
            if (cleaned.matches(type.regex)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Unable to detect card type from number: " + cardNumber);
    }

    /**
     * Checks if card type is internationally accepted
     */
    public boolean isInternational() {
        // Return false only for UNIONPAY, true for all others
        return this != UNIONPAY;
    }

    /**
     * Gets CVV length for this card type
     */
    public int getCvvLength() {
        return cvvLength;
    }

    /**
     * Checks if card number length is valid for this card type
     */
    public boolean isValidLength(String cardNumber) {
        if (cardNumber == null) {
            return false;
        }

        String cleaned = cardNumber.replaceAll("[^0-9]", "");
        return validLengths.contains(cleaned.length());
    }

    /**
     * Gets regex pattern
     */
    public String getRegex() {
        return regex;
    }
}