package com.gocartpay.model.orders.response;

import com.gocartpay.model.enums.OrderStatus;
import com.gocartpay.model.enums.SettlementType;
import com.gocartpay.utils.JsonUtil;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for an order response object
 */
public class Order {
    private int total;
    private int subtotal;
    private int tax;
    private int shipping;
    private int tip;
    private int cashAmount;
    public int pointsAmount;
    public int totalPoints;
    private String currencyCode;
    private String merchantOrderId;
    private String orderDescription;
    private String metadata;
    private OrderStatus status;
    private SettlementType settlementType;
    private String lastProcessingAttempt;
    private String customerId;
    private String paymentId;
    private String addressId;
    private String shippingAddressId;
    private String shippingMethodId;
    private String merchantId;
    private List<LineItemResponse> orderLineItems;
    private List<Transaction> transactions;
    private String storeId;
    private String hash;
    private String externalId;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(int cashAmount) {
        this.cashAmount = cashAmount;
    }

    public int getPointsAmount() {
        return pointsAmount;
    }

    public void setPointsAmount(int pointsAmount) {
        this.pointsAmount = pointsAmount;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public SettlementType getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(SettlementType settlementType) {
        this.settlementType = settlementType;
    }

    public String getLastProcessingAttempt() {
        return lastProcessingAttempt;
    }

    public void setLastProcessingAttempt(String lastProcessingAttempt) {
        this.lastProcessingAttempt = lastProcessingAttempt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(String shippingAddress) {
        this.shippingAddressId = shippingAddress;
    }

    public String getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(String shippingMethod) {
        this.shippingMethodId = shippingMethod;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public List<LineItemResponse> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<LineItemResponse> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return total == order.total && subtotal == order.subtotal && tax == order.tax && shipping == order.shipping && tip == order.tip && cashAmount == order.cashAmount && pointsAmount == order.pointsAmount && totalPoints == order.totalPoints && Objects.equals(currencyCode, order.currencyCode) && Objects.equals(merchantOrderId, order.merchantOrderId) && Objects.equals(orderDescription, order.orderDescription) && Objects.equals(metadata, order.metadata) && status == order.status && settlementType == order.settlementType && Objects.equals(lastProcessingAttempt, order.lastProcessingAttempt) && Objects.equals(customerId, order.customerId) && Objects.equals(paymentId, order.paymentId) && Objects.equals(addressId, order.addressId) && Objects.equals(shippingAddressId, order.shippingAddressId) && Objects.equals(shippingMethodId, order.shippingMethodId) && Objects.equals(merchantId, order.merchantId) && Objects.equals(orderLineItems, order.orderLineItems) && Objects.equals(transactions, order.transactions) && Objects.equals(storeId, order.storeId) && Objects.equals(hash, order.hash) && Objects.equals(externalId, order.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, subtotal, tax, shipping, tip, cashAmount, pointsAmount, totalPoints, currencyCode, merchantOrderId, orderDescription, metadata, status, settlementType, lastProcessingAttempt, customerId, paymentId, addressId, shippingAddressId, shippingMethodId, merchantId, orderLineItems, transactions, storeId, hash, externalId);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }

}
