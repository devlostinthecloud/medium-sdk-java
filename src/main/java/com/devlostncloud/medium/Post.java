package com.devlostncloud.medium;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.devlostncloud.medium.License.ALL_RIGHTS_RESERVED;
import static com.devlostncloud.medium.PostPublisher.mediumPublisher;
import static com.devlostncloud.medium.PublishStatus.DRAFT;
import static com.devlostncloud.medium.PublishStatus.PUBLIC;
import static com.devlostncloud.medium.PublishStatus.UNLISTED;

public final class Post {

    private Data data;

    private Post() { }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return data.getId();
    }

    public String getUrl() {
        return data.getUrl();
    }

    public String getAuthorId() {
        return data.getAuthorId();
    }

    public String getCanonicalUrl() {
        return data.getCanonicalUrl();
    }

    public String getTitle() {
        return data.getTitle();
    }

    public List<String> getTags() {
        return data.getTags();
    }

    public PublishStatus getPublishStatus() {
        return PublishStatus.from(data.getPublishStatus());
    }

    public String getPublishedAt() {
        return data.getPublishedAt();
    }

    public String getLicenseUrl() {
        return data.getLicenseUrl();
    }

    public License getLicense() {
        return License.from(data.getLicense());
    }

    public static class Builder {
        private String title;
        private Content content;
        private String authorId;
        private PublishStatus status = PUBLIC;
        private License license = ALL_RIGHTS_RESERVED;
        private Set<String> tags;
        private boolean notifyFollowers = false;
        private URL canonicalUrl;

        public Builder author(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(Content content) {
            this.content = content;
            return this;
        }

        public Builder tags(String... tags) {
            this.tags = Set.of(tags);
            return this;
        }

        public Builder canonicalUrl(URL canonicalUrl) {
            this.canonicalUrl = canonicalUrl;
            return this;
        }

        public Builder license(License license) {
            this.license = license;
            return this;
        }

        public Builder notifyFollowers() {
            this.notifyFollowers = true;
            return this;
        }

        public Builder asDraft() {
            this.status = DRAFT;
            return this;
        }

        public Builder asUnlisted() {
            this.status = UNLISTED;
            return this;
        }

        private Map<String, Object> params() {
            Map<String, Object> params = new HashMap<>();
            params.put("title", this.title);
            params.put("contentFormat", this.content.getFormat());
            params.put("content", this.content.getBody());
            if (tags != null) {
                params.put("tags", this.tags);
            }
            if (canonicalUrl != null) {
                params.put("canonicalUrl", this.canonicalUrl);
            }
            params.put("publishStatus", this.status.value());
            params.put("license", this.license.value());
            params.put("notifyFollowers", this.notifyFollowers);
            return params;
        }

        public Post publish() {
            return publishVia(mediumPublisher());
        }

        Post publishVia(PostPublisher postPublisher) {
            validate(this);
            return postPublisher.publish(authorId, params());
        }

        private void validate(Builder builder) {

            if (builder.authorId == null || "".equals(builder.authorId.trim())) {
                throw new IllegalArgumentException("author id is required");
            }

            if (builder.title == null || "".equals(builder.title.trim())) {
                throw new IllegalArgumentException("title is required");
            }

            if (builder.title.length() > 100) {
                throw new IllegalArgumentException("title exceeds 100 chars max length");
            }

            if (builder.content == null || builder.content.isEmpty()) {
                throw new IllegalArgumentException("content is required");
            }

            if (builder.tags != null) {

                if (builder.tags.size() > 3) {
                    throw new IllegalArgumentException("tags exceeds 3 max size");
                }

                for (String tag : tags) {
                    if (tag.length() > 25) {
                        throw new IllegalArgumentException("tag exceeds 25 chars max length");
                    }
                }
            }
        }
    }
}
