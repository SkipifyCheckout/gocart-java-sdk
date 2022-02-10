package com.gocartpay.model.customer;

import com.gocartpay.utils.JsonUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for a customer
 */
public class Customer {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String emailAddress;
    private final List<PaymentMethod> paymentMethods;
    private final List<Address> addresses;

    public Customer(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.emailAddress = builder.emailAddress;
        this.paymentMethods = builder.paymentMethods;
        this.addresses = builder.addresses;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public List<Address> getAddresses() {
        return addresses;
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
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(emailAddress, customer.emailAddress) && Objects.equals(paymentMethods, customer.paymentMethods) && Objects.equals(addresses, customer.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, emailAddress, paymentMethods, addresses);
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private final String phoneNumber;
        private final String emailAddress;
        private List<PaymentMethod> paymentMethods;
        private List<Address> addresses;

        public Builder(String phoneNumber, String emailAddress) {
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder paymentMethods(List<PaymentMethod> paymentMethods) {
            this.paymentMethods = paymentMethods;
            return this;
        }

        public Builder addresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }

    }

}
