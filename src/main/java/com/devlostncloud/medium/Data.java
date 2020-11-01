package com.devlostncloud.medium;

import java.util.ArrayList;
import java.util.List;

class Data {
    private String id;
    private String title;
    private String authorId;
    private String url;
    private String canonicalUrl;
    private List<String> tags = new ArrayList<>();
    private String publishStatus;
    private String publishedAt;
    private String license;
    private String licenseUrl;

    String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorId() {
        return authorId;
    }

    String getUrl() {
        return url;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getLicense() {
        return license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }
}
