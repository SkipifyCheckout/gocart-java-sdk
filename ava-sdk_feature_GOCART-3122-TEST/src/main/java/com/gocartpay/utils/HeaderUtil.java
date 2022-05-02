package com.gocartpay.utils;

import com.gocartpay.exceptions.InvalidRequestException;
import com.gocartpay.model.authentication.Signature;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.gocartpay.GoCart.*;
import static com.gocartpay.GoCart.APPLICATION_JSON;

/**
 * Provides util methods for helping build request headers
 */
public final class HeaderUtil {
    private static final String NO_SUCH_ALGORITHM_MESSAGE = "SHA-256 is not available on your JVM. Use Java 1.8 or later.";

    private static String hmacSignature(Signature signature) throws NoSuchAlgorithmException {
        String formattedSignature = signature.getMerchantIdentifier() + PIPE + signature.getApiKey() + PIPE
                + signature.getTimeStamp() + PIPE + signature.getNonce() + PIPE + signature.getRequestUri() + PIPE
                + signature.getRequestMethod() + PIPE + signature.getRequestBody();
        //remove all whitespace and convert to uppercase
        formattedSignature = StringUtil.removeWhiteSpace(formattedSignature).toUpperCase();
        //base 64 encode
        String base64EncodedString = Base64.getEncoder().encodeToString(formattedSignature.getBytes());
        MessageDigest digest = MessageDigest.getInstance(SHA_256);
        byte[] sha256Hash = digest.digest(base64EncodedString.getBytes(StandardCharsets.UTF_8));
        return convertBytesToHex(sha256Hash);
    }

    private static String convertBytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static Map<String, String> generateDefaultHttpHeaders() {
        Map<String, String> httpHeaders = new HashMap<>();
        httpHeaders.put("Accept", APPLICATION_JSON);
        httpHeaders.put("Content-Type", APPLICATION_JSON);
        return httpHeaders;
    }

    public static Map<String, String> generateHmacHttpHeaders(String merchantId, String apiKey, String requestUri,
                                                              String httpVerb, String body) {
        try {
            String timestamp = String.valueOf(Instant.now().toEpochMilli());
            String nonce = UUID.randomUUID().toString();
            Signature signature = new Signature.Builder(merchantId, apiKey, timestamp,
                    nonce, requestUri, httpVerb, body).build();

            String hmacSignature = hmacSignature(signature);

            Map<String, String> httpHeaders = new HashMap<>();
            httpHeaders.put("Accept", APPLICATION_JSON);
            httpHeaders.put("Content-Type", APPLICATION_JSON);
            httpHeaders.put("x-merchant-id", merchantId);
            httpHeaders.put("timestamp", timestamp);
            httpHeaders.put("nonce", nonce);
            httpHeaders.put("signature", hmacSignature);
            return httpHeaders;
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidRequestException(NO_SUCH_ALGORITHM_MESSAGE, e);
        }

    }

}
