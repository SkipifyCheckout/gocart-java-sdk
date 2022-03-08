package com.gocartpay.model.pagination.response;

import com.gocartpay.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for links withing a {@link PagedResponse}
 */
public class Links {
    private String previousPage;
    private String nextPage;
    private String firstPage;
    private String lastPage;

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Links links = (Links) o;
        return Objects.equals(previousPage, links.previousPage) && Objects.equals(nextPage, links.nextPage) && Objects.equals(firstPage, links.firstPage) && Objects.equals(lastPage, links.lastPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousPage, nextPage, firstPage, lastPage);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }
}
