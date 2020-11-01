package com.devlostncloud.medium;

import kong.unirest.json.JSONObject;

import java.net.URL;
import java.util.List;
import java.util.Set;

import static com.devlostncloud.medium.License.ALL_RIGHTS_RESERVED;
import static com.devlostncloud.medium.PublishStatus.PUBLIC;
import static java.lang.String.format;
import static kong.unirest.Unirest.post;

public final class MediumPost {

    private MediumData data;

    private MediumPost() {
    }

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
        private static final String MEDIUM_API_BASE_URL = "https://api.medium.com";
        private static final String MEDIUM_API_POSTS_PATH_FORMAT = "/v1/users/%s/posts";

        private String title;
        private Content content;
        private String authorId;
        private final PublishStatus status = PUBLIC;
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

        private JSONObject toJSON() {
            JSONObject json = new JSONObject();
            json.put("title", this.title);
            json.put("contentFormat", this.content.getFormat());
            json.put("content", this.content.getBody());
            if (tags != null) {
                json.put("tags", this.tags);
            }
            if (canonicalUrl != null) {
                json.put("canonicalUrl", this.canonicalUrl);
            }
            json.put("publishStatus", this.status.value());
            json.put("license", this.license.value());
            json.put("notifyFollowers", this.notifyFollowers);
            return json;
        }

        MediumPost publish(String baseUrl) {
            validate(this);
            return post(format(baseUrl + MEDIUM_API_POSTS_PATH_FORMAT, this.authorId))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Accept-Charset", "utf-8")
                    .body(this.toJSON())
                    .asObject(MediumPost.class)
                    .getBody();
        }

        public MediumPost publish() {
            return publish(MEDIUM_API_BASE_URL);
        }

        private void validate(Builder builder) {
            if (builder.title == null || "".equals(builder.title.trim())) {
                throw new IllegalArgumentException("title is required");
            }

            if (builder.title.length() > 100) {
                throw new IllegalArgumentException("title exceeds 100 chars max length");
            }

            if (builder.content == null || builder.content.isEmpty()) {
                throw new IllegalArgumentException("content is required");
            }

            if(builder.tags.size() > 3) {
                throw new IllegalArgumentException("tags exceeds 3 max size");
            }

            for (String tag : tags) {
              if(tag.length() > 25) {
                  throw new IllegalArgumentException("tag exceeds 25 chars max length");
              }
            }
        }
    }
}
