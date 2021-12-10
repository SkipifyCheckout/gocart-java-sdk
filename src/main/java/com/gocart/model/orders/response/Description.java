package com.gocart.model.orders.response;

import com.gocart.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for describing {@link PagedResponse} results
 */
public class Description {
    private int totalRecords;
    private int totalPages;
    private int pageNumber;
    private int pageSize;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return totalRecords == that.totalRecords && totalPages == that.totalPages && pageNumber == that.pageNumber && pageSize == that.pageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRecords, totalPages, pageNumber, pageSize);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }
}
