package com.gocartpay.model.orders.response;

import com.gocartpay.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

public class PurchaseDetail {
    private String label;
    private String description;
    private int level;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
        PurchaseDetail that = (PurchaseDetail) o;
        return level == that.level && Objects.equals(label, that.label) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, description, level);
    }
}
