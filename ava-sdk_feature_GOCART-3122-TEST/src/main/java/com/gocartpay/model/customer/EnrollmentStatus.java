package com.gocartpay.model.customer;

import com.gocartpay.model.enums.CustomerEnrollmentStatus;
import com.gocartpay.utils.JsonUtil;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * Defines the enrollment status of  customer
 */
public class EnrollmentStatus implements Serializable {
    private CustomerEnrollmentStatus status;

    public CustomerEnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerEnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentStatus that = (EnrollmentStatus) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }
}
