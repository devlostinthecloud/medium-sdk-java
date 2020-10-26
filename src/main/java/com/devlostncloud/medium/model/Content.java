package com.devlostncloud.medium.model;

public class Content {
    private static final String HTML_CONTENT = "html";
    private final String body;
    private final String format;

    Content(String format, String body) {
        this.body = body;
        this.format = format;
    }

    public static Content html(String body) {
        return new Content(HTML_CONTENT, body);
    }

    public String getBody() {
        return body;
    }

    public String getFormat() {
        return format;
    }
}
