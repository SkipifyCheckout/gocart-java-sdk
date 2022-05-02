package com.gocartpay.model.pagination.request;

import org.apache.hc.core5.http.NameValuePair;

import java.util.List;

/**
 * Defines the fields for needed to make an API call with pagination
 */
public class QueryString {
    private String encodedString;
    private List<NameValuePair> parameters;

    public QueryString(Builder builder) {
        this.encodedString = builder.encodedString;
        this.parameters = builder.parameters;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public List<NameValuePair> getParameters() {
        return parameters;
    }

    public static class Builder {
        private String encodedString;
        private List<NameValuePair> parameters;
        public Builder(){}

        public Builder encodedQueryString(String encodedString) {
            this.encodedString = encodedString;
            return this;
        }

        public Builder queryParameters(List<NameValuePair> parameters) {
            this.parameters = parameters;
            return this;
        }

        public QueryString build() { return new QueryString(this); }
    }
}
