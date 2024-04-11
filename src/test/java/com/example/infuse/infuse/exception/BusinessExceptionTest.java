package com.example.infuse.infuse.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BusinessExceptionTest {

    @Test
    void testThrowingExceptionWithoutMessage() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException();
        });
    }
}
