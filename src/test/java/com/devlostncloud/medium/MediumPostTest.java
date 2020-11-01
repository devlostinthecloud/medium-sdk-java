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

    @Test
    public void contentShouldValidateWhenEmpty() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> MediumPost.builder()
                        .title("Title")
                        .content(Content.html(""))
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("content is required");
    }

    @Test
    public void tagsShouldValidateListSize() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> MediumPost.builder()
                        .title("Title")
                        .content(Content.html("Some content"))
                        .tags("tag-1", "tag-2", "tag-3", "tag-4")
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("tags exceeds 3 max size");
    }

    @Test
    public void tagsShouldValidateItemLength() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> MediumPost.builder()
                        .title("Title")
                        .content(Content.html("Some content"))
                        .tags("tag-1", "this-tag-is-longer-than-25", "tag-3")
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("tag exceeds 25 chars max length");
    }

    @Test
    public void tagsShouldValidateNullItem() {

        assertThrows(NullPointerException.class,
                () -> MediumPost.builder()
                        .title("Title")
                        .content(Content.html("Some content"))
                        .tags(null, "tag-2", "tag-3")
                        .publish());
    }
}