package com.devlostncloud.medium;

import com.devlostncloud.medium.model.MediumPost;
import org.junit.jupiter.api.Test;

import static com.devlostncloud.medium.model.Content.html;
import static org.assertj.core.api.Assertions.assertThat;

class CreateMediumPostTest {
    @Test
    void createPostWithHtmlContent() {
        MediumPost post = MediumPost.Builder
                .create("How to use Medium API")
                .withContent(html("<h1>Medium API 1</h1>"))
                .publish();

        assertThat(post.getId()).isNotEmpty();
    }
}
