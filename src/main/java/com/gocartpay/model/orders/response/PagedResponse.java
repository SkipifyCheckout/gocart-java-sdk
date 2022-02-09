package com.gocartpay.model.orders.response;

import com.gocartpay.utils.JsonUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for a pagination response
 */
public class PagedResponse {
    private Description description;
    private Links links;
    private List<Order> data;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagedResponse that = (PagedResponse) o;
        return Objects.equals(description, that.description) && Objects.equals(links, that.links) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, links, data);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }
}
