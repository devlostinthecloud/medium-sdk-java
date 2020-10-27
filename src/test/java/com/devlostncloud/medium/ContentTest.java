package com.devlostncloud.medium;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {

    @Test
    void html() {
        Content htmlContent = Content.html("this is the post body");

        assertThat(htmlContent.getFormat()).isEqualTo("html");
        assertThat(htmlContent.getBody()).isEqualTo("this is the post body");
    }

    @Test
    public void contentShouldReturnIsEmpty() throws Exception {
        Content content = Content.html(" ");

        assertThat(content.isEmpty()).isEqualTo(true);
    }

    @Test
    public void contentShouldReturnIsEmptyWhenNull() throws Exception {
        Content content = Content.html(null);

        assertThat(content.isEmpty()).isEqualTo(true);
    }
}