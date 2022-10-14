package com.gocartpay.unit;

import com.gocartpay.GoCart;
import com.gocartpay.exceptions.GoCartApiException;
import com.gocartpay.exceptions.InvalidRequestException;
import com.gocartpay.model.GoCartProperties;
import com.gocartpay.model.customer.Address;
import com.gocartpay.model.customer.Customer;
import com.gocartpay.model.customer.EnrollmentStatus;
import com.gocartpay.net.GoCartRestClient;
import com.gocartpay.operation.CustomerActions;
import com.gocartpay.utils.HeaderUtil;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gocartpay.GoCart.*;
import static com.gocartpay.GoCartTestConstants.*;
import static com.gocartpay.model.enums.CustomerEnrollmentStatus.NEWLY_ENROLLED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Customer operations
 */
@ExtendWith(MockitoExtension.class)
public class CustomerActionsTest {
    @Mock
    private GoCartRestClient mockGoCartRestClient;
    @InjectMocks
    private CustomerActions customerActions;
    private URI customerUri;
    private Map<String, String> requestHeaders;

    @BeforeEach
    public void setup() {
        TestCenter.getInstance().configureTestCredentials(VALID_CREDENTIALS);
        try {
            customerUri = new URIBuilder(GOCART_API_BASE).setPath(FORWARD_SLASH + CUSTOMERS).build();
            requestHeaders = HeaderUtil.generateDefaultHttpHeaders();
            requestHeaders.put("x-merchant-id", goCartProperties.getMerchantId());
            requestHeaders.put("referer", goCartProperties.getReferer());
        } catch (URISyntaxException e) {
            System.out.println("GOCART_API_BASE is invalid");
        }
    }

    @Test
    @Tag(HAPPY_PATH)
    public void enrollCustomerTest() {
        Customer customer = buildNewCustomerRequest("2025551234");

        //set up mock return data
        EnrollmentStatus goodEnrollmentStatus = new EnrollmentStatus();
        goodEnrollmentStatus.setStatus(NEWLY_ENROLLED);

        when(mockGoCartRestClient.sendPost(customerUri, requestHeaders, customer,
                200, EnrollmentStatus.class)).thenReturn(goodEnrollmentStatus);

        assertThat(customerActions.enrollCustomer(customer)).isEqualTo(goodEnrollmentStatus);
    }

    @Test
    @Tag(SAD_PATH)
    public void enrollCustomerNullMerchantId() {
        GoCart.goCartProperties = new GoCartProperties.Builder()
                .merchantId(null)
                .apiKey(TEST_API_KEY)
                .truststorePath(System.getenv(GOCART_TRUSTSTORE_PATH))
                .truststorePassword(System.getenv(GOCART_TRUSTSTORE_PASSWORD))
                .build();

        Customer customer = buildNewCustomerRequest("2025551234");

        assertThatThrownBy(() -> {
            customerActions.enrollCustomer(customer);
        }).isInstanceOf(InvalidRequestException.class)
                .hasMessageContaining(NULL_MERCHANT_CREDENTIALS_MESSAGE);
    }

    @Test
    @Tag(SAD_PATH)
    public void enrollCustomerInvalidPhoneNumber() {
        Customer customer = buildNewCustomerRequest("2025551234");

        when(mockGoCartRestClient.sendPost(customerUri, requestHeaders, customer,
                200, EnrollmentStatus.class)).thenThrow(new GoCartApiException(
                String.format(MISMATCHED_RESPONSE_CODE_MESSAGE, 400,
                        "{\"error\":\"Invalid Phone Number: 2025551234\"}", 200)));

        assertThatThrownBy(() -> {
            customerActions.enrollCustomer(customer);
        }).isInstanceOf(GoCartApiException.class)
                .hasMessageContaining("Did not get expected response code from gocart-api");
    }

    private Customer buildNewCustomerRequest(String phoneNumber) {
        List<Address> addresses = new ArrayList<>();
        String firstName = "Jane";
        String lastName = "Doe";
        addresses.add(new Address.Builder(firstName, lastName,
                "123 Example Lane", "Denver", "Colorado",
                "80211", "USA").build());

        return new Customer.Builder(phoneNumber, "jane.doe@example.com")
                .firstName(firstName)
                .lastName(lastName)
                .addresses(addresses).build();
    }
}
