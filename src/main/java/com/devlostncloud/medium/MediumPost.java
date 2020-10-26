package com.devlostncloud.medium;

import kong.unirest.json.JSONObject;

import java.util.Map;

import static java.lang.String.format;
import static kong.unirest.Unirest.post;

public final class MediumPost {
    private static final String MEDIUM_API_BASE_URL = "https://api.medium.com";
    private static final String MEDIUM_API_POSTS_PATH_FORMAT = "/v1/users/%s/posts";

    private MediumData data;

    private MediumPost() { }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return data.getId();
    }

    public String getUrl() {
        return data.getUrl();
    }

    public static class Builder {
        private String baseUrl = MEDIUM_API_BASE_URL;
        private Map<String, String> postParams;
        private String title;
        private Content content;
        private String authorId;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(Content content) {
            this.content = content;
            return this;
        }

        public Builder author(String authorId) {
            this.authorId = authorId;
            return this;
        }

        Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        private JSONObject toJSON() {
            JSONObject json = new JSONObject();
            json.put("title", this.title);
            json.put("contentFormat", this.content.getFormat());
            json.put("content", this.content.getBody());
            return json;
        }

        public MediumPost publish() {
            validate(this);
            return post(format(baseUrl + MEDIUM_API_POSTS_PATH_FORMAT, this.authorId))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Accept-Charset", "utf-8")
                    .body(this.toJSON())
                    .asObject(MediumPost.class)
                    .getBody();
        }

        private void validate(Builder builder) {
            if (builder.title == null || "".equals(builder.title.trim())) {
                throw new IllegalArgumentException("title is required");
            }

            if (builder.title.length() > 100) {
                throw new IllegalArgumentException("title exceeds 100 chars max length");
            }
        }
    }
}