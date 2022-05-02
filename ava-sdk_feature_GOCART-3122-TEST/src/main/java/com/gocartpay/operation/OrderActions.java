package com.gocartpay.operation;

import com.gocartpay.exceptions.GoCartClientException;
import com.gocartpay.model.orders.response.Order;
import com.gocartpay.model.pagination.request.PaginationQuery;
import com.gocartpay.model.pagination.request.QueryString;
import com.gocartpay.model.pagination.response.PagedResponse;
import com.gocartpay.net.GoCartRestClient;
import com.gocartpay.utils.HeaderUtil;
import com.gocartpay.utils.QueryStringUtil;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static com.gocartpay.GoCart.*;

/**
 * Defines the gocart-api operations that a merchant can complete for orders using the sdk
 */
public class OrderActions {

    private final GoCartRestClient restClient;

    public OrderActions(GoCartRestClient client) {
        this.restClient = client;
    }

    /**
     *
     * @param paginationQuery - paginationQuery object
     * @return the paged response or orders that uses pagination query information
     */
    public PagedResponse getOrders(PaginationQuery paginationQuery) {
        goCartProperties.isValid();
        paginationQuery.isWithinRange();

        try {
            QueryString queryString = QueryStringUtil.buildQueryParameters(ORDERS, paginationQuery);
            URI orderUriWithParameters = new URIBuilder(GOCART_API_BASE).setPath(FORWARD_SLASH + ORDERS)
                    .addParameters(queryString.getParameters()).build();
            Map<String, String> requestHeaders = HeaderUtil.generateHmacHttpHeaders(goCartProperties.getMerchantId(),
                    goCartProperties.getApiKey(), queryString.getEncodedString(), GET, EMPTY_STRING);

            return restClient.sendGet(orderUriWithParameters, requestHeaders, 200,
                    PagedResponse.class);
        } catch (URISyntaxException e) {
            throw new GoCartClientException("Invalid URI", e);
        }
    }

    /**
     *
     * @return the default paged response of orders
     */
    public PagedResponse getOrders() {
        goCartProperties.isValid();

        try {

            URI orderBaseUrl = new URIBuilder(GOCART_API_BASE).setPath(FORWARD_SLASH + ORDERS).build();
            Map<String, String> requestHeaders = HeaderUtil.generateHmacHttpHeaders(goCartProperties.getMerchantId(),
                    goCartProperties.getApiKey(), ORDERS, GET, EMPTY_STRING);

            return restClient.sendGet(orderBaseUrl, requestHeaders, 200,
                    PagedResponse.class);

        } catch (URISyntaxException e) {
            throw new GoCartClientException("Invalid URI", e);
        }

    }

    /**
     *
     * @param orderId - orderId
     * @return a single order
     */
    public Order getSingleOrder(String orderId) {
        goCartProperties.isValid();
        try {
            String path = ORDERS + FORWARD_SLASH + orderId;
            URI orderBaseUrl = new URIBuilder(GOCART_API_BASE).setPath(FORWARD_SLASH + path).build();
            Map<String, String> requestHeaders = HeaderUtil.generateHmacHttpHeaders(
                    goCartProperties.getMerchantId(),
                    goCartProperties.getApiKey(),
                    path, GET, EMPTY_STRING);
            return restClient.sendGet(orderBaseUrl, requestHeaders, 200, Order.class);
        } catch (URISyntaxException e) {
            throw new GoCartClientException("Invalid URI", e);
        }
    }

}
