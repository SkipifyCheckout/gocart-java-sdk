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
        parametersList.add(new BasicNameValuePair("pageNumber", String.valueOf(paginationQuery.getPageNumber())));
        parametersList.add(new BasicNameValuePair("pageSize", String.valueOf(paginationQuery.getPageSize())));

        String encodedString = path + "?pageNumber=" + URLEncoder.encode(String.valueOf(paginationQuery.getPageNumber()), StandardCharsets.UTF_8)
                + "&pageSize=" + URLEncoder.encode(String.valueOf(paginationQuery.getPageSize()), StandardCharsets.UTF_8);

        return new QueryString.Builder()
                .queryParameters(parametersList)
                .encodedQueryString(encodedString).build();
    }

}
