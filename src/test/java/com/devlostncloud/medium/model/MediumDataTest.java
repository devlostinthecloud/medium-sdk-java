package com.devlostncloud.medium.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MediumDataTest {

    @Test
    void getId() {
        String id = "post-id";
        MediumData data = new MediumData();
        data.setId(id);

        assertThat(data.getId()).isEqualTo(id);
    }

    @Test
    void getUrl() {
        String url = "https://medium.com/post-id";
        MediumData data = new MediumData();
        data.setUrl(url);

        assertThat(data.getUrl()).isEqualTo(url);
    }
}