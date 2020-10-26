package com.devlostncloud.medium.model;

public class MediumPost {
    private final String title;
    private final Content content;
    private final String id;

    private MediumPost(String title, Content content) {
        this.id = "someId";
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public static class Builder {
        private String title;
        private Content content;

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

        public MediumPost publish() {
            return new MediumPost(title, content);
        }
    }
}
