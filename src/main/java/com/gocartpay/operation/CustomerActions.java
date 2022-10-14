package com.gocartpay.operation;

import com.gocartpay.exceptions.GoCartClientException;
import com.gocartpay.model.customer.Customer;
import com.gocartpay.model.customer.EnrollmentStatus;
import com.gocartpay.net.GoCartRestClient;
import com.gocartpay.utils.HeaderUtil;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static com.gocartpay.GoCart.*;

/**
 * Defines the gocart-api operations that a merchant can complete for customers using the sdk
 */
public class CustomerActions {

    private final GoCartRestClient restClient;

    public CustomerActions(GoCartRestClient goCartRestClient) {
        this.restClient = goCartRestClient;
    }

    /**
     *
     * @param customer
     * @return the enrollment status of the customer
     */
    public EnrollmentStatus enrollCustomer(Customer customer) {
        goCartProperties.isValid();

        try {
            URI customerBaseUrl = new URIBuilder(GOCART_API_BASE).setPath(FORWARD_SLASH + CUSTOMERS).build();
            Map<String, String> requestHeaders = HeaderUtil.generateDefaultHttpHeaders();
            requestHeaders.put("x-merchant-id", goCartProperties.getMerchantId());
            requestHeaders.put("referer", goCartProperties.getReferer());
            EnrollmentStatus enrollmentStatus = restClient.sendPost(customerBaseUrl, requestHeaders, customer,
                    200, EnrollmentStatus.class);

            return enrollmentStatus;
        } catch (URISyntaxException e) {
            throw new GoCartClientException("Invalid URI", e);
        }
    }

}
