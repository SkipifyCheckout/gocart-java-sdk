package com.gocartpay.utils;

import static com.gocartpay.GoCart.EMPTY_STRING;

/**
 * Provides util methods for helping with strings
 */
public final class StringUtil {
    public static String removeWhiteSpace(String string) {
        return string.replaceAll("[\\r\\n\\t\\s]", EMPTY_STRING);
    }
}
