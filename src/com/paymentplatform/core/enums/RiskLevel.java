package com.paymentplatform.core.enums;

public enum RiskLevel {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL;


    public boolean requiresManualReview() {
        return this == HIGH || this == CRITICAL;
    }

    public boolean requiresImmediateAction() {
        return this == CRITICAL;
    }


    public RiskLevel getHigher(RiskLevel other) {
        if (other == null) {
            return this; //
        }
        return this.ordinal() >= other.ordinal() ? this : other;


    }


    public int getSeverityScore() {
        return ordinal();
    }

    public boolean isHigherThan(RiskLevel other) {
        return other != null && ordinal() > other.ordinal();
    }

    public boolean isLowerThan(RiskLevel other) {
        return other != null && ordinal() < other.ordinal();
    }


    public String getRecommendedAction() {
        switch (this) {
            case LOW:
                return "Process normally";
            case MEDIUM:
                return "Additional verification";
            case HIGH:
                return "Manual review required";
            case CRITICAL:
                return "Block transaction, alert security";
            default:
                throw new IllegalStateException("Unexpected RiskLevel: " + this);
        }
    }


    public String getColorCode() {
        switch (this) {
            case LOW:
                return "#00FF00"; // green
            case MEDIUM:
                return "#FFFF00"; // yellow
            case HIGH:
                return "#FFA500"; // orange
            case CRITICAL:
                return "#FF0000"; // red
            default:
                throw new IllegalStateException("Unexpected RiskLevel: " + this);
        }
    }
}