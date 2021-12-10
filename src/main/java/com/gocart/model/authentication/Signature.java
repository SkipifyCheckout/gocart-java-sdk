package com.gocart.model.authentication;

import com.gocart.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines the necessary fields for building the HMAC signature for rest requests
 */
public class Signature {
    private final String merchantIdentifier;
    private final String apiKey;
    private final String timeStamp;
    private final String nonce;
    private final String requestUri;
    private final String requestMethod;
    private final String requestBody;

    public Signature(Builder builder) {
        this.merchantIdentifier = builder.merchantIdentifier;
        this.apiKey = builder.apiKey;
        this.timeStamp = builder.timeStamp;
        this.nonce = builder.nonce;
        this.requestUri = builder.requestUri;
        this.requestMethod = builder.requestMethod;
        this.requestBody = builder.requestBody;
    }

    public String getMerchantIdentifier() {
        return merchantIdentifier;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getNonce() {
        return nonce;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Signature signature = (Signature) o;
        return Objects.equals(merchantIdentifier, signature.merchantIdentifier) && Objects.equals(apiKey, signature.apiKey) && Objects.equals(timeStamp, signature.timeStamp) && Objects.equals(nonce, signature.nonce) && Objects.equals(requestUri, signature.requestUri) && Objects.equals(requestMethod, signature.requestMethod) && Objects.equals(requestBody, signature.requestBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantIdentifier, apiKey, timeStamp, nonce, requestUri, requestMethod, requestBody);
    }

    @Override
    public String toString() {
        Optional<String> string = JsonUtil.toString(this);
        return string.orElse(null);
    }

    public static class Builder {
        private final String merchantIdentifier;
        private final String apiKey;
        private final String timeStamp;
        private final String nonce;
        private final String requestUri;
        private final String requestMethod;
        private final String requestBody;

        public Builder(String merchantIdentifier, String apiKey, String timeStamp, String nonce, String requestUri,
                       String requestMethod, String requestBody) {
            this.merchantIdentifier = merchantIdentifier;
            this.apiKey = apiKey;
            this.timeStamp = timeStamp;
            this.nonce = nonce;
            this.requestUri = requestUri;
            this.requestMethod = requestMethod;
            this.requestBody = requestBody;
        }

        public Signature build() {
            return new Signature(this);
        }
    }

}
