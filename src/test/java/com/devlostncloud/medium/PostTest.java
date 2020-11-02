package com.devlostncloud.medium;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PostTest {

    @Test
    void authorShouldValidateEmpty() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Post.builder()
                        .author("  ")
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("author id is required");
    }

    @Test
    void authorShouldValidateNull() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Post.builder()
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("author id is required");
    }

    @Test
    void titleShouldValidateMaxLength() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Post.builder()
                        .author("aid")
                        .title(randomAlphanumeric(101))
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("title exceeds 100 chars max length");
    }

    @Test
    void titleShouldValidateEmpty() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Post.builder()
                        .author("aid")
                        .title("  ")
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("title is required");
    }

    @Test
    void titleShouldValidateNull() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Post.builder()
                        .author("aid")
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("title is required");
    }

    @Test
    public void contentShouldValidateWhenEmpty() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Post.builder()
                        .author("aid")
                        .title("Title")
                        .content(Content.html(""))
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("content is required");
    }

    @Test
    public void tagsShouldValidateListSize() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Post.builder()
                        .author("aid")
                        .title("Title")
                        .content(Content.html("Some content"))
                        .tags("tag-1", "tag-2", "tag-3", "tag-4")
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("tags exceeds 3 max size");
    }

    @Test
    public void tagsShouldValidateItemLength() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Post.builder()
                        .author("aid")
                        .title("Title")
                        .content(Content.html("Some content"))
                        .tags("tag-1", "this-tag-is-longer-than-25", "tag-3")
                        .publish());

        assertThat(exception.getMessage()).isEqualTo("tag exceeds 25 chars max length");
    }

    @Test
    public void tagsShouldValidateNullItem() {

        assertThrows(NullPointerException.class,
                () -> Post.builder()
                        .author("aid")
                        .title("Title")
                        .content(Content.html("Some content"))
                        .tags(null, "tag-2", "tag-3")
                        .publish());
    }

    @Test
    public void shouldSetPublishStatusAsDraft() {

        PostPublisher mockPublisher = Mockito.mock(PostPublisher.class);
        ArgumentCaptor<Map> paramsCaptured = ArgumentCaptor.forClass(Map.class);
        Post mockPost = Mockito.mock(Post.class);

        when(mockPublisher.publish(anyString(), anyMap())).thenReturn(mockPost);

        Post.builder()
                .author("aid")
                .title("Title")
                .content(Content.html("content"))
                .asDraft()
                .publishVia(mockPublisher);

        verify(mockPublisher).publish(eq("aid"), paramsCaptured.capture());
        Map<String, Object> params = paramsCaptured.getValue();
        assertThat(params).containsEntry("publishStatus", "draft");
    }

    @Test
    public void shouldSetPublishStatusAsUnlisted() {

        PostPublisher mockPublisher = Mockito.mock(PostPublisher.class);
        ArgumentCaptor<Map> paramsCaptured = ArgumentCaptor.forClass(Map.class);
        Post mockPost = Mockito.mock(Post.class);

        when(mockPublisher.publish(anyString(), anyMap())).thenReturn(mockPost);

        Post.builder()
                .author("aid")
                .title("Title")
                .content(Content.html("content"))
                .asUnlisted()
                .publishVia(mockPublisher);

        verify(mockPublisher).publish(eq("aid"), paramsCaptured.capture());
        Map<String, Object> params = paramsCaptured.getValue();
        assertThat(params).containsEntry("publishStatus", "unlisted");
    }
}