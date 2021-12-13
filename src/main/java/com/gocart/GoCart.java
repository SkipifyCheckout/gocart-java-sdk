package com.gocart;

import com.gocart.model.GoCartProperties;

public abstract class GoCart {

    public static String GOCART_API_BASE = "https://api.gocartpay.com";

    public static GoCartProperties goCartProperties = new GoCartProperties.Builder()
            .merchantId(System.getenv("GOCART_MERCHANTID"))
            .apiKey(System.getenv("GOCART_APIKEY"))
            .truststorePath(System.getenv("GOCART_TRUSTSTORE_PATH"))
            .truststorePassword(System.getenv("GOCART_TRUSTSTORE_PASSWORD")).build();

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
            " keys: GOCART_MERCHANTID, GOCART_APIKEY before" +
            " attempting to use the sdk";
    public static final String MISMATCHED_RESPONSE_CODE_MESSAGE = "Did not get expected response code from gocart-api."+
            " Got %s status code with message %s. The expected response code was %s";
    public static String USE_TRUST_STRATEGY = System.getenv("USE_TRUST_STRATEGY");
}