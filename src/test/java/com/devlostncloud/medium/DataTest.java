package com.devlostncloud.medium;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DataTest {

    @Test
    void getTagsDefaultsToEmpty() {
        Data data = new Data();
        assertThat(data.getTags()).isEmpty();
    }
}