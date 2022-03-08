package com.gocartpay.model.pagination.request;

import com.gocartpay.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines the fields for a pagination query to refine PagedResponse results
 */
public class PaginationQuery {
    private int pageNumber;
    private int pageSize;

    public PaginationQuery(Builder builder){
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public String toString()
    {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationQuery that = (PaginationQuery) o;
        return pageNumber == that.pageNumber && pageSize == that.pageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize);
    }

    public static class Builder
    {
        private int pageNumber;
        private int pageSize;
        public Builder(){}

        public Builder pageNumber(int pageNumber){
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder pageSize(int pageSize){
            this.pageSize = pageSize;
            return this;
        }

        public PaginationQuery build() { return new PaginationQuery(this); }
    }

    public void isWithinRange(){
        if (this.pageSize > 50){
            System.out.println(String.format("The max page size for GoCart API is 50. You specified a page size of %d " +
                    "but only 50 records maximum will be returned per page", this.pageSize));
        }
        if (this.pageSize < 0)
        {
            System.out.println(String.format("Negative page sizes are not valid. You provided a page size of %d " +
                    "but GoCart API will default the page size to 25.", this.pageSize));
        }
    }
}
