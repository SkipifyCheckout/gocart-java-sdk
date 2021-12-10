package com.gocart.model.orders.request;

import com.gocart.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for a line item
 */
public class LineItemRequest {
    private final String sku;
    private final String description;
    private final String amount;
    private final String category;

    public LineItemRequest(Builder builder) {
        this.sku = builder.sku;
        this.description = builder.description;
        this.amount = builder.amount;
        this.category = builder.category;
    }

    public String getSku() {
        return sku;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItemRequest lineItem = (LineItemRequest) o;
        return Objects.equals(sku, lineItem.sku) && Objects.equals(description, lineItem.description) && Objects.equals(amount, lineItem.amount) && Objects.equals(category, lineItem.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, description, amount, category);
    }

    public static class Builder {
        private final String sku;
        private String description;
        private String amount;
        private String category;

        public Builder(String sku) {
            this.sku = sku;
        }

        private Builder description(String description) {
            this.description = description;
            return this;
        }

        private Builder amount(String amount) {
            this.amount = amount;
            return this;
        }

        private Builder category(String category) {
            this.category = category;
            return this;
        }

        public LineItemRequest build() {
            return new LineItemRequest(this);
        }
    }

}
