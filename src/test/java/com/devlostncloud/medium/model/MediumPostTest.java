package com.devlostncloud.medium.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MediumPostTest {

    @Test
    void getData() {
        MediumData data = new MediumData();
        MediumPost post = new MediumPost();
        post.setData(data);

        assertThat(post.getData()).isSameAs(data);
    }
}