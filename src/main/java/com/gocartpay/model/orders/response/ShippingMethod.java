package com.gocartpay.model.orders.response;

import com.gocartpay.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for a shipping method
 */
public class ShippingMethod {
    private String name;
    private int price;
    private String externalId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingMethod that = (ShippingMethod) o;
        return price == that.price && Objects.equals(name, that.name) && Objects.equals(externalId, that.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, externalId);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }


}
