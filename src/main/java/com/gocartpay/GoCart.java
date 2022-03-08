package com.gocartpay;

import com.gocartpay.model.GoCartProperties;

import java.util.Objects;

public abstract class GoCart {

    public static String GOCART_STAGING_URL = "https://api-staging.gocartpay.com";
    public static String GOCART_API_BASE = Objects.requireNonNullElse(System.getenv("GOCART_API_BASE_URL"), GOCART_STAGING_URL);

    public static GoCartProperties goCartProperties = new GoCartProperties.Builder()
            .merchantId(System.getenv("GOCART_MERCHANT_ID"))
            .apiKey(System.getenv("GOCART_API_KEY"))
            .truststorePath(System.getenv("GOCART_TRUSTSTORE_PATH"))
            .truststorePassword(System.getenv("GOCART_TRUSTSTORE_PASSWORD")).build();

    public static final String EMPTY_STRING = "";
    public static final String FORWARD_SLASH = "/";
    public static final String PIPE = "|";
    public static final String SHA_256 = "SHA-256";

    //customers endpoints
    public static final String CUSTOMERS = "customers";

    //orders endpoints
    public static final String ORDERS = "orders";
    public static final String VOID = "void";

    public static final String APPLICATION_JSON = "application/json";

    //REST verbs
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String PUT = "PUT";

    public static final String NULL_MERCHANT_CREDENTIALS_MESSAGE = "Merchant Id and/or Api key arenull." +
            " Create environment variables for these values with the following" +
            " keys: GOCART_MERCHANT_ID, GOCART_API_KEY before" +
            " attempting to use the sdk";
    public static final String MISMATCHED_RESPONSE_CODE_MESSAGE = "Did not get expected response code from gocart-api."+
            " Got %s status code with message %s. The expected response code was %s";
    public static String USE_TRUST_STRATEGY = System.getenv("USE_TRUST_STRATEGY");
}