package com.devlostncloud.medium;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MediumDataTest {

    @Test
    void getTagsDefaultsToEmpty() {
        MediumData mediumData = new MediumData();
        assertThat(mediumData.getTags()).isEmpty();
    }
}