package com.gocart.operation;

import com.gocart.exceptions.GoCartClientException;
import com.gocart.model.orders.request.PaginationQuery;
import com.gocart.model.orders.response.Order;
import com.gocart.model.orders.response.PagedResponse;
import com.gocart.net.GoCartRestClient;
import com.gocart.utils.HeaderUtil;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Defines the gocart-api operations that a merchant can complete for orders using the sdk
 */
import static com.gocart.GoCart.*;

public class OrderActions {

    private final GoCartRestClient restClient;

    public OrderActions(GoCartRestClient client) {
        this.restClient = client;
    }

    /**
     *
     * @param paginationQuery
     * @return the paged response or orders that uses pagination query information
     */
    public PagedResponse getOrders(PaginationQuery paginationQuery) {
        goCartProperties.isValid();
        paginationQuery.isWithinRange();

        try {
            URI orderUriWithParameters = new URIBuilder(GOCART_API_BASE).setPath(FORWARD_SLASH + ORDERS)
                    .addParameters(buildQueryParameters(paginationQuery)).build();
            Map<String, String> requestHeaders = HeaderUtil.generateHmacHttpHeaders(goCartProperties.getMerchantId(),
                    goCartProperties.getApiKey(), ORDERS, GET, "");

            return restClient.sendGet(orderUriWithParameters, requestHeaders, 200,
                    PagedResponse.class);
        } catch (URISyntaxException e) {
            throw new GoCartClientException("Invalid URI", e);
        }
    }

    /**
     *
     * @return the defualt paged response of orders
     */
    public PagedResponse getOrders() {
        goCartProperties.isValid();

        try {

            URI orderBaseUrl = new URIBuilder(GOCART_API_BASE).setPath(FORWARD_SLASH + ORDERS).build();
            Map<String, String> requestHeaders = HeaderUtil.generateHmacHttpHeaders(goCartProperties.getMerchantId(),
                    goCartProperties.getApiKey(), ORDERS, GET, "");

            return restClient.sendGet(orderBaseUrl, requestHeaders, 200,
                    PagedResponse.class);

        } catch (URISyntaxException e) {
            throw new GoCartClientException("Invalid URI", e);
        }

    }

    /**
     *
     * @param orderId
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
                    path, GET, "");
            return restClient.sendGet(orderBaseUrl, requestHeaders, 200, Order.class);
        } catch (URISyntaxException e) {
            throw new GoCartClientException("Invalid URI", e);
        }
    }

    private List<NameValuePair> buildQueryParameters(PaginationQuery paginationQuery) {
        List<NameValuePair> parametersList = new ArrayList<>();
        parametersList.add(new BasicNameValuePair("pageSize", String.valueOf(paginationQuery.getPageSize())));
        parametersList.add(new BasicNameValuePair("pageNumber", String.valueOf(paginationQuery.getPageNumber())));
        return parametersList;
    }

}
