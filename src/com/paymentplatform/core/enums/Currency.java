package com.paymentplatform.core.enums;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public enum Currency {
    USD("USD", "$", 2),
    EUR("EUR", "€", 2),
    GBP("GBP", "£", 2),
    JPY("JPY", "¥", 0),
    CAD("CAD", "C$", 2),
    AUD("AUD", "A$", 2),
    CHF("CHF", "Fr", 2),
    CNY("CNY", "¥", 2),
    INR("INR", "₹", 2),
    BRL("BRL", "R$", 2),
    MXN("MXN", "$", 2),
    SGD("SGD", "S$", 2),
    HKD("HKD", "HK$", 2),
    NZD("NZD", "NZ$", 2),
    KRW("KRW", "₩", 0);

    private final String code;
    private final String symbol;
    private final int decimalPlaces;

    Currency(String code, String symbol, int decimalPlaces) {
        this.code = code;
        this.symbol = symbol;
        this.decimalPlaces = decimalPlaces;
    }

    public static Currency fromCode(String code) {
        for(Currency currency : Currency.values()) {
            if(currency.code.equals(code)) {
                return currency;
            }
        }

        throw new IllegalArgumentException("Unknown currency code: " + code);
    }

    public String format(BigDecimal amount){
        if(amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        BigDecimal roundedAmount = amount.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);

        return this.symbol + roundedAmount.toString();
    }

    public static List<String> getSupportedCodes() {
        List <String> codes = new ArrayList<>();

        for(Currency currency : Currency.values()) {
            codes.add(currency.code);
        }

        return codes;
    }

    public boolean isDefaultCurrency() {
        return this == USD;
    }



    public boolean isSupported() {
        return true;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }
}
