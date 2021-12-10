package com.gocart.model;

import com.gocart.exceptions.InvalidRequestException;
import com.gocart.utils.JsonUtil;

import java.util.Objects;
import java.util.Optional;

import static com.gocart.GoCart.NULL_MERCHANT_CREDENTIALS_MESSAGE;

/**
 * Defines the fields for configuring merchant information necessary for using the sdk
 */
public class GoCartProperties {
    private final String merchantId;
    private final String apiKey;
    private final String truststorePath;
    private final String truststorePassword;

    public GoCartProperties(Builder builder) {
        this.merchantId = builder.merchantId;
        this.apiKey = builder.apiKey;
        this.truststorePath = builder.truststorePath;
        this.truststorePassword = builder.truststorePassword;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getTruststorePath() {
        return truststorePath;
    }

    public String getTruststorePassword() {
        return truststorePassword;
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
        GoCartProperties that = (GoCartProperties) o;
        return Objects.equals(merchantId, that.merchantId) && Objects.equals(apiKey, that.apiKey) && Objects.equals(truststorePath, that.truststorePath) && Objects.equals(truststorePassword, that.truststorePassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantId, apiKey, truststorePath, truststorePassword);
    }

    public static class Builder {
        private String merchantId;
        private String apiKey;
        private String truststorePath;
        private String truststorePassword;

        public Builder() {
        }

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder truststorePath(String truststorePath) {
            this.truststorePath = truststorePath;
            return this;
        }

        public Builder truststorePassword(String truststorePassword) {
            this.truststorePassword = truststorePassword;
            return this;
        }

        public GoCartProperties build() {
            return new GoCartProperties(this);
        }

    }

    public void isValid() {
        if (this.merchantId == null || this.apiKey == null) {
            throw new InvalidRequestException(NULL_MERCHANT_CREDENTIALS_MESSAGE);
        }
    }

}
