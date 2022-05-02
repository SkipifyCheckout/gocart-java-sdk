package com.gocartpay.model.orders.request;

import com.gocartpay.utils.JsonUtil;

import java.util.Optional;

/**
 * Defines the fields for purchase details on an order
 */
public class PurchaseDetailsRequest {
    private final String label;
    private final String description;
    private final int level;

    public PurchaseDetailsRequest(Builder builder) {
        this.label = builder.label;
        this.description = builder.description;
        this.level = builder.level;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }


    public static class Builder {
        private final int level;
        private String label;
        private String description;

        public Builder(int level) {
            this.level = level;
        }

        private Builder label(String label) {
            this.label = label;
            return this;
        }

        private Builder description(String description) {
            this.description = description;
            return this;
        }

        public PurchaseDetailsRequest build() {
            return new PurchaseDetailsRequest(this);
        }
    }

}
