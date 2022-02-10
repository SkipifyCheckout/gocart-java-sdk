package com.gocartpay.model.orders.response;


import com.gocartpay.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for a line item response object
 */
public class LineItemResponse {
    private String sku;
    private String description;
    private String amount;
    private String category;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItemResponse that = (LineItemResponse) o;
        return Objects.equals(sku, that.sku) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, description, amount, category);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }
}
