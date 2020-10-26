package com.devlostncloud.medium.model;

import static kong.unirest.Unirest.*;

public class MediumPost {

    private MediumData data;

    public MediumData getData() {
        return data;
    }

    public void setData(MediumData data) {
        this.data = data;
    }

    public static class Builder {
        private String title;
        private Content content;
        private String authorId;
        private String baseUrl;

        private Builder(String title) {
            this.title = title;
        }

        public static Builder create(String title) {
            return new Builder(title);
        }

        public Builder withContent(Content content) {
            this.content = content;
            return this;
        }

        public Builder author(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public MediumPost publish() {
            return post(baseUrl + "/v1/users/" + authorId + "/posts")
                    .header("Accept", "application/json")
                    .field("title", title)
                    .field("contentFormat", content.getFormat())
                    .field("content", content.getBody())
                    .asObject(MediumPost.class)
                    .getBody();
        }
    }
}
