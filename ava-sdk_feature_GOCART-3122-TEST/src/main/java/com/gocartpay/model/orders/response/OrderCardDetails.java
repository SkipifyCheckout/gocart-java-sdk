package com.gocartpay.model.orders.response;

import com.gocartpay.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

public class OrderCardDetails {
    private String cardBrand;
    private String last4CardNumber;
    private int expMonth;
    private int expYear;

    public String getCardBrand() { return cardBrand; }

    public void setCardBrand(String cardBrand) { this.cardBrand = cardBrand; }

    public String getLast4CardNumber() { return last4CardNumber; }

    public void setLast4CardNumber(String last4CardNumber) { this.last4CardNumber = last4CardNumber; }

    public int getExpMonth() { return expMonth; }

    public void setExpMonth(int expMonth) { this.expMonth = expMonth; }

    public int getExpYear() { return expYear; }

    public void setExpYear(int expYear) { this.expYear = expYear; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCardDetails that = (OrderCardDetails) o;
        return expMonth == that.expMonth && expYear == that.expYear && Objects.equals(cardBrand, that.cardBrand) && Objects.equals(last4CardNumber, that.last4CardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardBrand, last4CardNumber, expMonth, expYear);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }
}
