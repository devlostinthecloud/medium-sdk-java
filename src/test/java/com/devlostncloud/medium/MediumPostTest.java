package com.devlostncloud.medium;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MediumPostTest {

    @Test
    void titleShouldValidateMaxLength() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> MediumPost.builder()
                        .title(randomAlphanumeric(101))
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("title exceeds 100 chars max length");
    }

    @Test
    void titleShouldValidateEmptyTitle() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> MediumPost.builder()
                        .title("  ")
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("title is required");
    }

    @Test
    void titleShouldValidateNullTitle() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> MediumPost.builder()
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("title is required");
    }
}