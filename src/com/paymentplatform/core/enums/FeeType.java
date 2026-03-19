package com.paymentplatform.core.enums;

public enum FeeType {
    FLAT,
    PERCENTAGE,
    TIERED,
    INTERCHANGE_PLUS,
    BLENDED;


    public boolean isPercentageBased() {
        return this == PERCENTAGE || this == INTERCHANGE_PLUS || this == BLENDED;
    }

    public boolean isFlatBased() {
        return this == FLAT;
    }

    public boolean isDynamic() {
        return this == TIERED || this == INTERCHANGE_PLUS;
    }

    public String getDescription() {
        switch (this) {
            case FLAT:
                return "Fixed fee amount per transaction";
            case PERCENTAGE:
                return "Percentage of transaction amount";
            case TIERED:
                return "Fee varies based on transaction tiers or brackets";
            case INTERCHANGE_PLUS:
                return "Interchange fee plus a fixed markup";
            case BLENDED:
                return "Combination of fixed and percentage-based fees";
            default:
                throw new IllegalStateException("Unexpected FeeType: " + this);
        }
    }
}