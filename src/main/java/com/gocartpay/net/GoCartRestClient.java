package com.gocartpay.net;

import com.gocartpay.exceptions.GoCartApiException;
import com.gocartpay.exceptions.GoCartClientException;
import com.gocartpay.utils.JsonUtil;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.BasicHttpContext;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;

import static com.gocartpay.GoCart.*;

/**
 * Rest client that communicates with the GoCart Api
 */
public class GoCartRestClient {

    private static final String MALFORMED_JSON_RESPONSE_MESSAGE = "Received an invalid json object from gocart-api." +
            " Response code was %s but cannot map the response json to the provided return object.";
    private static final String PARSE_EXCEPTION_MESSAGE = "Failed to parse HTTP response entity to a UTF-8 String";
    private static final String FAILED_TO_CLOSE_CLIENT_MESSAGE = "Failed to close rest client";

    public GoCartRestClient() {
    }

    /**
     *
     * @param uri destination for sending GET request
     * @param requestHeaders headers for request
     * @param expectedResponseCode response code expected to be returned
     * @param clazz Object.class to map the response body to
     * @param <T> Object.class to map the response body to
     * @return Object Type passed in as clazz
     * @exception GoCartApiException if the received response code did not match the expected response code
     * @exception GoCartClientException if there was any issue parsing the response body to UTF-8 or closing rest client after response
     */
    public <T> T sendGet(URI uri, Map<String, String> requestHeaders, int expectedResponseCode,
                         Class<T> clazz) {
        return sendRequest(uri, GET, requestHeaders, null, expectedResponseCode, clazz);
    }

    /**
     *
     * @param uri destination for sending POST request
     * @param requestHeaders headers for request
     * @param body request body
     * @param expectedResponseCode response code expected to be returned
     * @param clazz Object.class to map the response body to
     * @param <T> Object.class to map the response body to
     * @return Object Type passed in as clazz
     * @exception GoCartApiException if the received response code did not match the expected response code
     * @exception GoCartClientException if there was any issue parsing the response body to UTF-8 or closing rest client after response
     */
    public <T> T sendPost(URI uri, Map<String, String> requestHeaders, Object body, int expectedResponseCode,
                          Class<T> clazz) {
        return sendRequest(uri, POST, requestHeaders, body, expectedResponseCode, clazz);
    }

    /**
     *
     * @param uri destination for sending PUT request
     * @param requestHeaders headers for request
     * @param body request body
     * @param expectedResponseCode response code expected to be returned
     * @param clazz Object.class to map the response body to
     * @param <T> Object.class to map the response body to
     * @return Object Type passed in as clazz
     * @exception GoCartApiException if the received response code did not match the expected response code
     * @exception GoCartClientException if there was any issue parsing the response body to UTF-8 or closing rest client after response
     */
    public <T> T sendPut(URI uri, Map<String, String> requestHeaders, Object body, int expectedResponseCode,
                         Class<T> clazz) {

        return sendRequest(uri, PUT, requestHeaders, body, expectedResponseCode, clazz);
    }

    private <T> T sendRequest(URI uri, String httpVerb, Map<String, String> requestHeaders, Object body, int expectedResponseCode,
                              Class<T> clazz) {
        CloseableHttpClient restClient = httpsClient();
        CloseableHttpResponse response = null;
        HttpContext context = new BasicHttpContext();
        try {
            switch (httpVerb) {
                case POST:
                    HttpPost httpPost = new HttpPost(uri);
                    requestHeaders.forEach(httpPost::addHeader);
                    httpPost.setEntity(new StringEntity(body.toString()));
                    response = restClient.execute(httpPost);
                    break;
                case GET:
                    HttpGet httpGet = new HttpGet(uri);
                    requestHeaders.forEach(httpGet::addHeader);
                    response = restClient.execute(httpGet, context);
                    break;
                case PUT:
                    HttpPut httpPut = new HttpPut(uri);
                    requestHeaders.forEach(httpPut::addHeader);
                    httpPut.setEntity(new StringEntity(body.toString()));
                    response = restClient.execute(httpPut);
                    break;
            }
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            if (response.getCode() != expectedResponseCode) {
                throw new GoCartApiException(String.format(MISMATCHED_RESPONSE_CODE_MESSAGE,
                        response.getCode(), responseBody, expectedResponseCode));
            }

            EntityUtils.consume(responseEntity);
            return mapApiResponseResponseToReturnObject(responseBody, clazz, response);
        } catch (IOException e) {
            throw new GoCartClientException(e);
        } catch (ParseException e) {
            throw new GoCartClientException(PARSE_EXCEPTION_MESSAGE);
        } finally {
            closeRestClientAndResponse(restClient, response);
        }
    }

    private <T> T mapApiResponseResponseToReturnObject(String apiResponseBody, Class<T> returnObject,
                                                       CloseableHttpResponse response) {
        try {
            return JsonUtil.fromJson(apiResponseBody, returnObject);
        } catch (IOException e) {
            throw new GoCartApiException(String.format(MALFORMED_JSON_RESPONSE_MESSAGE, response.getCode()), e);
        }
    }

    private void closeRestClientAndResponse(CloseableHttpClient restClient, CloseableHttpResponse response) {
        try {
            restClient.close();
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            throw new GoCartClientException(FAILED_TO_CLOSE_CLIENT_MESSAGE, e);
        }
    }

    KeyStore readStore() throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
        FileInputStream keyStoreStream = new FileInputStream(goCartProperties.getTruststorePath());
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(keyStoreStream, goCartProperties.getTruststorePassword().toCharArray());
        return keyStore;
    }

    private CloseableHttpClient httpsClient() {
        try {
            SSLContext sslContext;
            if (Boolean.parseBoolean(USE_TRUST_STRATEGY)) {
                sslContext = SSLContexts.custom()
                        .loadTrustMaterial(readStore(), null)
                        .build();
            } else {
                TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
                sslContext = SSLContexts.custom()
                        .loadTrustMaterial(null, acceptingTrustStrategy)
                        .build();
            }

            final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                    .setSslContext(sslContext)
                    .build();
            final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                    .setSSLSocketFactory(sslSocketFactory)
                    .build();

            CloseableHttpClient httpsClient = HttpClients.custom()
                    .setConnectionManager(cm)
                    .build();
            return httpsClient;
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException |
                CertificateException e) {
            e.printStackTrace();
            throw new GoCartClientException(e.getMessage(), e);
        }
    }
}
