package com.gocart.util;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

/**
 * Provides fake email data for unit tests
 */
public final class FakerUtil {
    public static String generateRandomEmail() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-US"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        return email;
    }
}
