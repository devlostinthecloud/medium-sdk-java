package com.devlostncloud.medium;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ContentTest {

    @Test
    void html() {
        Content htmlContent = Content.html("this is the post body");

        Assertions.assertThat(htmlContent.getFormat()).isEqualTo("html");
        Assertions.assertThat(htmlContent.getBody()).isEqualTo("this is the post body");
    }
}