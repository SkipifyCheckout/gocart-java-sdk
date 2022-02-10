package com.gocartpay.model.orders.response;

import com.gocartpay.model.enums.TransactionStatus;
import com.gocartpay.model.enums.TransactionType;
import com.gocartpay.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for a transation
 */
public class Transaction {
    private String gatewayTransactionId;
    private int amount;
    private TransactionType type;
    private TransactionStatus status;
    private String orderId;
    private String externalId;

    public String getGatewayTransactionId() {
        return gatewayTransactionId;
    }

    public void setGatewayTransactionId(String gatewayTransactionId) {
        this.gatewayTransactionId = gatewayTransactionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
        Transaction that = (Transaction) o;
        return amount == that.amount && Objects.equals(gatewayTransactionId, that.gatewayTransactionId) && type == that.type && status == that.status && Objects.equals(orderId, that.orderId) && Objects.equals(externalId, that.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gatewayTransactionId, amount, type, status, orderId, externalId);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }

}
