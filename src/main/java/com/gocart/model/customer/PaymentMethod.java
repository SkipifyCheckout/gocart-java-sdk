package com.gocart.model.customer;

import com.gocart.utils.JsonUtil;

import java.util.Optional;

/**
 * Defines the fields for a payment method
 */
public class PaymentMethod {
    private final String nickName;
    private final String fullNameCard;
    private final String cardNumber;
    private final int expMonth;
    private final int expYear;
    private final String cvv;
    private final boolean isDefault;
    private final Address address;

    private PaymentMethod(Builder builder) {
        this.nickName = builder.nickName;
        this.fullNameCard = builder.fullNameCard;
        this.cardNumber = builder.cardNumber;
        this.expMonth = builder.expMonth;
        this.expYear = builder.expYear;
        this.cvv = builder.cvv;
        this.isDefault = builder.isDefault;
        this.address = builder.address;
    }

    public String getNickName() {
        return nickName;
    }

    public String getFullNameCard() {
        return fullNameCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public String getCvv() {
        return cvv;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }

    public static class Builder {
        private String nickName;
        private final String fullNameCard;
        private final String cardNumber;
        private final int expMonth;
        private final int expYear;
        private final String cvv;
        private boolean isDefault;
        private Address address;

        public Builder(String fullNameCard, String cardNumber, int expMonth,
                       int expYear, String cvv) {
            this.fullNameCard = fullNameCard;
            this.cardNumber = cardNumber;
            this.expMonth = expMonth;
            this.expYear = expYear;
            this.cvv = cvv;
        }

        public Builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder isDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;

        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }

    }

}
