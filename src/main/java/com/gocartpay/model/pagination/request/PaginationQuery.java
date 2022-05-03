package com.gocartpay.model.pagination.request;

import java.util.Objects;


/**
 * Defines the fields for a pagination query to refine PagedResponse results
 */
public class PaginationQuery {
    private Integer pageNumber;
    private Integer pageSize;
    private String orderId;

    public PaginationQuery(Builder builder){
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
        this.orderId = builder.orderId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationQuery that = (PaginationQuery) o;
        return Objects.equals(pageNumber, that.pageNumber) && Objects.equals(pageSize, that.pageSize) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize, orderId);
    }

    public static class Builder
    {
        private Integer pageNumber;
        private Integer pageSize;
        private String orderId;
        public Builder(){}

        public Builder pageNumber(Integer pageNumber){
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder pageSize(Integer pageSize){
            this.pageSize = pageSize;
            return this;
        }

        public Builder orderId(String orderId){
            this.orderId = orderId;
            return this;
        }

        public PaginationQuery build() { return new PaginationQuery(this); }
    }

    public void isWithinRange(){
        if (this.pageSize != null && this.pageSize > 50){
            System.out.println(String.format("The max page size for GoCart API is 50. You specified a page size of %d " +
                    "but only 50 records maximum will be returned per page", this.pageSize));
        }
        if (this.pageSize != null && this.pageSize < 0)
        {
            System.out.println(String.format("Negative page sizes are not valid. You provided a page size of %d " +
                    "but GoCart API will default the page size to 25.", this.pageSize));
        }
    }
}
