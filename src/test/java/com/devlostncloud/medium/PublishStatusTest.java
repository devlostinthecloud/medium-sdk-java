package com.devlostncloud.medium;

import org.junit.jupiter.api.Test;

import static com.devlostncloud.medium.PublishStatus.DRAFT;
import static com.devlostncloud.medium.PublishStatus.PUBLIC;
import static com.devlostncloud.medium.PublishStatus.UNLISTED;
import static org.assertj.core.api.Assertions.assertThat;

class PublishStatusTest {

    @Test
    void values() {
        assertThat(PublishStatus.values()).containsExactly(PUBLIC, DRAFT, UNLISTED);
    }

    @Test
    public void shouldReturnPublicValue() {
        assertThat(PUBLIC.value()).isEqualTo("public");
    }

    @Test
    public void shouldReturnDraftValue() {
        assertThat(DRAFT.value()).isEqualTo("draft");
    }

    @Test
    public void shouldReturnUnlistedValue() {
        assertThat(UNLISTED.value()).isEqualTo("unlisted");
    }

    @Test
    public void shouldReturnStatusFromValue() {
        assertThat(PublishStatus.from("draft")).isEqualTo(DRAFT);
    }

    @Test
    public void shouldReturnNullAsStatusFromNonRecognisedValue() {
        assertThat(PublishStatus.from("something-else")).isNull();
    }
}