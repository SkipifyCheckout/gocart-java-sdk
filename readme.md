# GoCart Java Client Library

## Requirements

- Java 1.8 or later
- Merchant Id and Merchant Api Key. These should have been provided to you in onboarding.
- GoCart-Api certificate

### Maven Dependency

```the maven dependency will live here once the project jar gets uploaded to Central Maven Repository```

### Gradle Dependency

```the gradle dependency will live here```

### If you would rather manually install the JAR
Link to download the JAR will go here

## Documentation

Refer to [GoCart Documentation](https://docs.gocartpay.com/docs) for additional information that is not included in this readme.

### Configuration Required To Use The SDK
It is necessary to add the GoCartApi Certificate to a truststore and then create an environment variable with the path to it. 
The sdk will need this information in order to communicate with the GoCart API securely.

**If you are a GoCart Employee** You will need to download a file in teams under Engineering > gocartsdkstore.
Keep track of the path on your machine to gocartsdkstore because you will need it in the following step. 
I will remove this section of the readme before putting on github.

**You will need to create the following environment variables:**
- GOCART_MERCHANTID = {your_merchant_id}
- GOCART_APIKEY = {your_api_key}
- GOCART_TRUSTSTORE_PATH = {path_to_gocartsdkstore}
- GOCART_TRUSTSTORE_PASSWORD = gocartjavasdk

### Authentication
The GoCart APi uses HMAC to authenticate most requests. The gocart-java-sdk will do HMAC authorization for you.
You just need to have your [properties set](#Configuration-Required-To-Use-The-Sdk).

## Examples of How To Use The SDK

### Enroll Customer

```java
import com.gocart.GoCart;
import com.gocart.exceptions.GoCartException;
import com.gocart.model.customer.Address;
import com.gocart.model.customer.Customer;
import com.gocart.model.customer.EnrollmentStatus;
import com.gocart.net.GoCartRestClient;
import com.gocart.operation.CustomerActions;

import java.util.ArrayList;
import java.util.List;

public class GoCartEnrollCustomerExample {
    public static void main(String[] args) {
        Address customersAddress = new Address.Builder("FirstName", "LastName",
                "123 Example Lane", "Denver", "CO",
                "54321", "USA").build();

        List<Address> addresses = new ArrayList<>();
        addresses.add(customersAddress);

        Customer customer = new Customer.Builder("1234567890", "email@example.com")
                .firstName("FirstName")
                .lastName("LastName")
                .addresses(addresses)
                .build();

        CustomerActions customerActions = new CustomerActions(new GoCartRestClient());
        try {
            EnrollmentStatus enrollmentStatus = customerActions.enrollCustomer(customer);
            System.out.println(enrollmentStatus);
        } catch (GoCartException e) {
            e.printStackTrace();
        }
    }
}
```

### Get a Single Order

```java
import com.gocart.exceptions.GoCartException;
import com.gocart.model.orders.response.Order;
import com.gocart.net.GoCartRestClient;
import com.gocart.operation.OrderActions;

import java.util.List;

public class GoCartGetASingleOrderExample {
    
    public static void main(String[] args) {
        OrderActions orderActions = new OrderActions(new GoCartRestClient());
        try {
            String orderId = "exampleOrderId";
            Order order = orderActions.getSingleOrder(orderId);
            System.out.println("Order with id " + orderId + ": " + order);
        } catch (GoCartException e) {
            e.printStackTrace();
        }
    }
}
```

### Get All Orders

```java
import com.gocart.exceptions.GoCartException;
import com.gocart.model.orders.request.PaginationQuery;
import com.gocart.model.orders.response.Order;
import com.gocart.model.orders.response.PagedResponse;
import com.gocart.net.GoCartRestClient;
import com.gocart.operation.OrderActions;

import java.util.List;

public class GoCartGetAllOrdersExample {

    public PagedResponse getAllOrdersDefaultPagination() {
        OrderActions orderActions = new OrderActions(new GoCartRestClient());
        try {
            /*calling getOrders without a pagination query will return a paged response that starts on page 1
             with a default page size of 25*/
            PagedResponse pagedResponse = orderActions.getOrders();
            return pagedResponse.getData();
        } catch (GoCartException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrdersWithPaginationQuery() {
        OrderActions orderActions = new OrderActions(new GoCartRestClient());
        try {
            PaginationQuery paginationRequest = new PaginationQuery.Builder().pageSize(10).pageNumber(3).build();
            PagedResponse allOrdersResponse = orderActions.getOrders(paginationRequest);
            return allOrdersResponse.getData();
        } catch (GoCartException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("First 25 records on page 1: " + getAllOrders());
            System.out.println("These are the ten orders on page 3 of the all orders response: " + getAllOrdersWithPaginationQuery());
        } catch (GoCartException e) {
            e.printStackTrace();
        }
    }
}

```




