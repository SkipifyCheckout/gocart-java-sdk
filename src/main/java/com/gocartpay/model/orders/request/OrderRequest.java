package com.gocartpay.model.orders.request;

import com.gocartpay.utils.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for an order request
 */
public class OrderRequest {
    private final String orderId;
    private final String merchantId;
    private final String customerId;
    private final int subtotal;
    private final int tax;
    private final int shipping;
    private final String currencyCode;
    private final String orderDescription;
    private final Map<String, String> metadata;
    private final List<LineItemRequest> lineItems;

    public OrderRequest(Builder builder) {
        this.orderId = builder.orderId;
        this.merchantId = builder.merchantId;
        this.customerId = builder.customerId;
        this.subtotal = builder.subtotal;
        this.tax = builder.tax;
        this.shipping = builder.shipping;
        this.currencyCode = builder.currencyCode;
        this.orderDescription = builder.orderDescription;
        this.metadata = builder.metadata;
        this.lineItems = builder.lineItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getTax() {
        return tax;
    }

    public int getShipping() {
        return shipping;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public List<LineItemRequest> getLineItems() {
        return lineItems;
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
        OrderRequest that = (OrderRequest) o;
        return subtotal == that.subtotal && tax == that.tax && shipping == that.shipping && Objects.equals(orderId, that.orderId) && Objects.equals(merchantId, that.merchantId) && Objects.equals(customerId, that.customerId) && Objects.equals(currencyCode, that.currencyCode) && Objects.equals(orderDescription, that.orderDescription) && Objects.equals(metadata, that.metadata) && Objects.equals(lineItems, that.lineItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, merchantId, customerId, subtotal, tax, shipping, currencyCode, orderDescription, metadata, lineItems);
    }

    public static class Builder {
        private final String orderId;
        private final String merchantId;
        private String customerId;
        private final int subtotal;
        private int tax;
        private int shipping;
        private String currencyCode;
        private final String orderDescription;
        private Map<String, String> metadata;
        private List<LineItemRequest> lineItems;

        public Builder(String orderId, String merchantId, int subtotal, String orderDescription) {
            this.orderId = orderId;
            this.merchantId = merchantId;
            this.subtotal = subtotal;
            this.orderDescription = orderDescription;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder tax(int tax) {
            this.tax = tax;
            return this;
        }

        public Builder shipping(int shipping) {
            this.shipping = shipping;
            return this;
        }

        public Builder currencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        public Builder lineItems(List<LineItemRequest> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public OrderRequest build() {
            return new OrderRequest(this);
        }
    }
}
