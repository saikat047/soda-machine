package com.company.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void isEmpty_behavesAsExpected() {
        assertTrue(Utils.isEmpty(null));
        assertTrue(Utils.isEmpty(""));

        assertFalse(Utils.isEmpty(" ")); // Whitespace is not considered empty by String.isEmpty()
        assertFalse(Utils.isEmpty("hello"));
    }

    @Test
    void verifyNotEmpty_throwsForNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Utils.verifyNotEmpty(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Utils.verifyNotEmpty("");
        });
    }

    @Test
    void verifyNotEmpty_doesNotThrowForContentOrWhitespace() {
        assertDoesNotThrow(() -> {
            Utils.verifyNotEmpty(" ");
        });

        assertDoesNotThrow(() -> {
            Utils.verifyNotEmpty("hello");
        });
    }
}