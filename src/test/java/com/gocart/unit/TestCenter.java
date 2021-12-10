package com.gocart.unit;

import com.gocart.GoCart;
import com.gocart.model.GoCartProperties;

import static com.gocart.GoCartTestConstants.*;

/**
 * Test configuration class
 */
public class TestCenter {
    public static TestCenter instance = new TestCenter();

    public static TestCenter getInstance() {
        return instance;
    }

    public void configureTestCredentials(String testCredentials) {
        switch (testCredentials) {
            case INVALID_MERCHANT_ID:
                GoCart.goCartProperties = new GoCartProperties.Builder()
                        .merchantId("invalidMerchantId")
                        .apiKey(TEST_API_KEY)
                        .truststorePath(System.getenv(GOCART_TRUSTSTORE_PATH))
                        .truststorePassword(System.getenv(GOCART_TRUSTSTORE_PASSWORD))
                        .build();
                break;
            case INVALID_MERCHANT_API_KEY:
                GoCart.goCartProperties = new GoCartProperties.Builder()
                        .merchantId(TEST_MERCHANT_ID)
                        .apiKey("invalidApiKey")
                        .truststorePath(System.getenv(GOCART_TRUSTSTORE_PATH))
                        .truststorePassword(System.getenv(GOCART_TRUSTSTORE_PASSWORD))
                        .build();
                break;
            case VALID_CREDENTIALS:
                GoCart.goCartProperties = new GoCartProperties.Builder()
                        .merchantId(TEST_MERCHANT_ID)
                        .apiKey(TEST_API_KEY)
                        .truststorePath(System.getenv(GOCART_TRUSTSTORE_PATH))
                        .truststorePassword(System.getenv(GOCART_TRUSTSTORE_PASSWORD))
                        .build();
                break;
        }
    }
}
