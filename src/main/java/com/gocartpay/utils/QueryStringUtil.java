package com.gocartpay.utils;

import com.gocartpay.model.pagination.request.PaginationQuery;
import com.gocartpay.model.pagination.request.QueryString;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Provides util methods for helping build request headers
 */
public final class QueryStringUtil {

    public static QueryString buildQueryParameters(String path, PaginationQuery paginationQuery) {
        // Query string parameters always need to be in alphabetical order
        List<NameValuePair> parametersList = new ArrayList<>();

        String encodedString = path;
        if (paginationQuery.getOrderId() != null ) {
            parametersList.add(new BasicNameValuePair("orderId", String.valueOf(paginationQuery.getOrderId())));
            encodedString = encodedString + "?orderId=" + URLEncoder.encode(paginationQuery.getOrderId(), StandardCharsets.UTF_8);
        }

        if (paginationQuery.getPageNumber() != null){
            parametersList.add(new BasicNameValuePair("pageNumber", String.valueOf(paginationQuery.getPageNumber())));
            if (encodedString.equals(path)) {
                encodedString = encodedString + "?pageNumber=" + URLEncoder.encode(String.valueOf(paginationQuery.getPageNumber()), StandardCharsets.UTF_8);
            } else {
                encodedString = encodedString + "&pageNumber=" + URLEncoder.encode(String.valueOf(paginationQuery.getPageNumber()), StandardCharsets.UTF_8);
            }

        }
        if (paginationQuery.getPageSize() != null){
            parametersList.add(new BasicNameValuePair("pageSize", String.valueOf(paginationQuery.getPageSize())));
            if (encodedString.equals(path)) {
                encodedString = encodedString + "?pageSize=" + URLEncoder.encode(String.valueOf(paginationQuery.getPageSize()), StandardCharsets.UTF_8);
            } else {
                encodedString = encodedString + "&pageSize=" + URLEncoder.encode(String.valueOf(paginationQuery.getPageSize()), StandardCharsets.UTF_8);
            }
        }

        return new QueryString.Builder()
                .queryParameters(parametersList)
                .encodedQueryString(encodedString).build();
    }

}
