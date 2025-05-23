package com.company.util;

public class Utils {
    public static boolean isEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    public static void verifyNotEmpty(final String value) {
        if (isEmpty(value)) {
            throw new IllegalArgumentException("The given value is empty");
        }
    }
}
