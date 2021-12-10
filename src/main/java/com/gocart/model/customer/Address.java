package com.gocart.model.customer;

import com.gocart.utils.JsonUtil;

import java.util.Optional;

/**
 * Defines the fields for an address
 */
public class Address {
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String address1;
    private final String address2;
    private final String city;
    private final String state;
    private final String zip;
    private final String country;
    private final String phoneNumber;

    private Address(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.company = builder.company;
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.city = builder.city;
        this.state = builder.state;
        this.zip = builder.zip;
        this.country = builder.country;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }

    public static class Builder {
        private final String firstName;
        private final String lastName;
        private String company;
        private final String address1;
        private String address2;
        private final String city;
        private final String state;
        private final String zip;
        private final String country;
        private String phoneNumber;

        public Builder(String firstName, String lastName, String address1, String city, String state,
                       String zip, String country) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address1 = address1;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.country = country;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder address2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

}
