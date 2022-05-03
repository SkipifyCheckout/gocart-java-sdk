package com.gocartpay.unit;

import com.gocartpay.exceptions.GoCartApiException;
import com.gocartpay.exceptions.InvalidRequestException;
import com.gocartpay.model.orders.response.PurchaseDetail;
import com.gocartpay.model.pagination.request.PaginationQuery;
import com.gocartpay.model.pagination.response.Description;
import com.gocartpay.model.orders.response.Order;
import com.gocartpay.model.pagination.response.PagedResponse;
import com.gocartpay.net.GoCartRestClient;
import com.gocartpay.operation.OrderActions;
import com.gocartpay.util.FileUtil;
import com.gocartpay.utils.JsonUtil;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.gocartpay.GoCart.*;
import static com.gocartpay.GoCartTestConstants.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Order operations
 */
@ExtendWith(MockitoExtension.class)
public class OrderActionsTest {
    @Mock
    private GoCartRestClient mockGoCartRestClient;
    @InjectMocks
    private OrderActions orderActions;
    private URI ordersUri;
    private URI voidOrderUri;
    private URI singleOrderUri;
    private final String testOrderId = "orderIdForUnitTests";

    @BeforeEach
    public void setup() {
        try {
            ordersUri = new URIBuilder(GOCART_STAGING_URL).setPath(FORWARD_SLASH + ORDERS).build();
            singleOrderUri = new URIBuilder(ordersUri).appendPath(testOrderId).build();
            voidOrderUri = new URIBuilder(singleOrderUri).appendPath(VOID).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            fail("GOCART_API_BASE is invalid");
        }
        TestCenter.getInstance().configureTestCredentials(VALID_CREDENTIALS);
    }

    @Test
    @Tag(HAPPY_PATH)
    public void getAllOrders() {

        PagedResponse goodGetAllOrdersResponse = new PagedResponse();
        Order order = getTestOrder();
        List<Order> orders = List.of(order);

        goodGetAllOrdersResponse.setData(orders);

        when(mockGoCartRestClient.sendGet(eq(ordersUri), any(), eq(200),
                eq(PagedResponse.class))).thenReturn(goodGetAllOrdersResponse);

        assertThat(orderActions.getOrders())
                .isEqualTo(goodGetAllOrdersResponse);
    }

    @Test
    @Tag(HAPPY_PATH)
    public void getAllOrdersWithPageSize() {

        PagedResponse goodGetAllOrdersResponse = new PagedResponse();
        Order order = getTestOrder();
        List<Order> orders = List.of(order);
        goodGetAllOrdersResponse.setDescription(new Description());
        goodGetAllOrdersResponse.getDescription().setPageNumber(1);
        goodGetAllOrdersResponse.getDescription().setPageSize(45);
        goodGetAllOrdersResponse.setData(orders);

        PaginationQuery paginationQuery = new PaginationQuery.Builder().pageSize(45).build();

        when(mockGoCartRestClient.sendGet(any(), any(), eq(200),
                eq(PagedResponse.class))).thenReturn(goodGetAllOrdersResponse);

        assertThat(orderActions.getOrders(paginationQuery).getDescription().getPageSize())
                .isEqualTo(goodGetAllOrdersResponse.getDescription().getPageSize());
    }

    @Test
    @Tag(HAPPY_PATH)
    public void getAllOrdersWithPageNumber() {

        PagedResponse goodGetAllOrdersResponse = new PagedResponse();
        Order order = getTestOrder();
        List<Order> orders = List.of(order);
        goodGetAllOrdersResponse.setDescription(new Description());
        goodGetAllOrdersResponse.getDescription().setPageNumber(1);
        goodGetAllOrdersResponse.getDescription().setPageSize(25);
        goodGetAllOrdersResponse.setData(orders);

        PaginationQuery paginationQuery = new PaginationQuery.Builder().pageSize(1).build();

        when(mockGoCartRestClient.sendGet(any(), any(), eq(200),
                eq(PagedResponse.class))).thenReturn(goodGetAllOrdersResponse);

        assertThat(orderActions.getOrders(paginationQuery).getDescription().getPageNumber())
                .isEqualTo(goodGetAllOrdersResponse.getDescription().getPageNumber());
    }

    @Test
    @Tag(SAD_PATH)
    public void getAllOrdersInvalidMerchantId() {
        TestCenter.getInstance().configureTestCredentials(INVALID_MERCHANT_ID);

        when(mockGoCartRestClient.sendGet(eq(ordersUri), any(), eq(200),
                eq(PagedResponse.class))).thenThrow(new GoCartApiException(
                String.format(MISMATCHED_RESPONSE_CODE_MESSAGE, 401,
                        EMPTY_STRING, 200)));

        assertThatThrownBy(() -> orderActions.getOrders()).isInstanceOf(GoCartApiException.class)
                .hasMessageContaining("Did not get expected response code from gocart-api")
                .hasMessageContaining("Got 401 status code");
    }

    @Test
    @Tag(HAPPY_PATH)
    public void getSingleOrder() {
        Order goodOrderResponse = getTestOrder();
        goodOrderResponse.setExternalId(testOrderId);

        when(mockGoCartRestClient.sendGet(eq(singleOrderUri), any(), eq(200),
                eq(Order.class))).thenReturn(goodOrderResponse);

        Order result = orderActions.getSingleOrder(testOrderId);

        assertThat(result).isEqualTo(goodOrderResponse);
        assertThat(result.getOrderCardDetails()).isNotNull();
    }

    @Test
    @Tag(HAPPY_PATH)
    public void getSingleOrderWithPurchaseDetails() {
        Order goodOrderResponse = getTestOrderWithPurchaseDetails();
        goodOrderResponse.setExternalId(testOrderId);

        when(mockGoCartRestClient.sendGet(eq(singleOrderUri), any(), eq(200),
                eq(Order.class))).thenReturn(goodOrderResponse);

        Order result = orderActions.getSingleOrder(testOrderId);

        assertThat(result).isEqualTo(goodOrderResponse);
        assertThat(result.getPurchaseDetails()).isNotNull();
        assertTrue(result.getPurchaseDetails().size() > 0 );
    }

    @Test
    @Tag(HAPPY_PATH)
    public void getSingleOrderWithoutCardDetails() {
        Order goodOrderResponse = getTestOrderNoCard();
        goodOrderResponse.setExternalId(testOrderId);

        when(mockGoCartRestClient.sendGet(eq(singleOrderUri), any(), eq(200),
                eq(Order.class))).thenReturn(goodOrderResponse);

        Order result = orderActions.getSingleOrder(testOrderId);

        assertThat(result).isEqualTo(goodOrderResponse);
        assertThat(result.getOrderCardDetails()).isNull();
    }

    @Test
    @Tag(SAD_PATH)
    public void getSingleOrderBadApiKey() throws URISyntaxException {
        TestCenter.getInstance().configureTestCredentials(INVALID_MERCHANT_API_KEY);

        when(mockGoCartRestClient.sendGet(eq(singleOrderUri), any(), eq(200),
                eq(Order.class))).thenThrow(new GoCartApiException(
                String.format(MISMATCHED_RESPONSE_CODE_MESSAGE, 401,
                        EMPTY_STRING, 200)));

        assertThatThrownBy(() -> orderActions.getSingleOrder(testOrderId)).isInstanceOf(GoCartApiException.class)
                .hasMessageContaining("Did not get expected response code from gocart-api")
                .hasMessageContaining("Got 401 status code");
    }

    @Test
    @Tag(SAD_PATH)
    public void getSingleOrderInvalidId() {
        when(mockGoCartRestClient.sendGet(eq(singleOrderUri), any(), eq(200),
                eq(Order.class))).thenThrow(new GoCartApiException(
                String.format(MISMATCHED_RESPONSE_CODE_MESSAGE, 404,
                        EMPTY_STRING, 200)));

        assertThatThrownBy(() -> orderActions.getSingleOrder(testOrderId))
                .isInstanceOf(GoCartApiException.class)
                .hasMessageContaining("Did not get expected response code from gocart-api")
                .hasMessageContaining("Got 404 status code");
    }

    private Order getTestOrder() {
        return getTestOrder("TestOrder.json");
    }

    private Order getTestOrderWithPurchaseDetails() {
        Order order = getTestOrder("TestOrder.json");

        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setLabel("Test label");
        purchaseDetail.setDescription("Test description");
        purchaseDetail.setLevel(1);

        List<PurchaseDetail> purchaseDetails = new ArrayList<>();
        purchaseDetails.add(purchaseDetail);

        order.setPurchaseDetails(purchaseDetails);
        return order;
    }

    private Order getTestOrderNoCard() {
        return getTestOrder("TestOrderNoCard.json");
    }

    private Order getTestOrder(String testCardFile) {
        try {
            String orderString = FileUtil.readFile(testCardFile);
            return JsonUtil.fromJson(orderString, Order.class);
        } catch (IOException e) {
            throw new InvalidRequestException(e);
        }
    }
}
