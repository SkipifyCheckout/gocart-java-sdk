package com.gocartpay.unit;

import com.gocartpay.model.pagination.request.PaginationQuery;
import com.gocartpay.model.pagination.request.QueryString;
import com.gocartpay.utils.QueryStringUtil;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.gocartpay.GoCartTestConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Unit tests for QueryStringUtil
 */
public class QueryStringUtilTest {

    private final int pageSize = (int)(Math.random() * 50 + 1);
    private final int pageNumber = (int)(Math.random() * 50 + 1);
    private final Charset UTF_8 = StandardCharsets.UTF_8;
    private final RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('a', 'z').build();
    private final String path = generator.generate((int)(Math.random() * 50 + 1));

    @Test
    @Tag(HAPPY_PATH)
    public void getEncodedQueryString_PaginationQuery_Pass() {
        PaginationQuery paginationQuery = new PaginationQuery.Builder().pageSize(pageSize).pageNumber(pageNumber).build();

        String encodedString = path + "?pageNumber=" + URLEncoder.encode(String.valueOf(pageNumber), StandardCharsets.UTF_8)
                + "&pageSize=" + URLEncoder.encode(String.valueOf(pageSize), StandardCharsets.UTF_8);

        List<NameValuePair> parametersList = new ArrayList<>();
        parametersList.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));
        parametersList.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));

        QueryString queryString = QueryStringUtil.buildQueryParameters(path, paginationQuery);

        assertThat(queryString.getEncodedString()).isEqualTo(encodedString);
        assertThat(queryString.getParameters().get(0)).isEqualTo(parametersList.get(0));
        assertThat(queryString.getParameters().get(1)).isEqualTo(parametersList.get(1));
    }

    @Test
    @Tag(SAD_PATH)
    public void getEncodedQueryString_PaginationQuery_ListOrder_Fail() {
        PaginationQuery paginationQuery = new PaginationQuery.Builder().pageSize(pageSize).pageNumber(pageNumber).build();

        List<NameValuePair> parametersList = new ArrayList<>();
        parametersList.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
        parametersList.add(new BasicNameValuePair("pageNumber", String.valueOf(pageNumber)));

        QueryString queryString = QueryStringUtil.buildQueryParameters(path, paginationQuery);

        assertThat(queryString.getParameters().get(0)).isNotEqualTo(parametersList.get(0));
        assertThat(queryString.getParameters().get(1)).isNotEqualTo(parametersList.get(1));
    }

}
