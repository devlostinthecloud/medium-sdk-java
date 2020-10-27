package com.devlostncloud.medium;

public final class Content {
    private static final String HTML_CONTENT = "html";
    private final String body;
    private final String format;

    private Content(String format, String body) {
        this.body = body;
        this.format = format;
    }

    public static Content html(String body) {
        return new Content(HTML_CONTENT, body);
    }

    String getBody() {
        return body;
    }

    String getFormat() {
        return format;
    }

    boolean isEmpty() {
        return this.body == null || "".equals(this.body.trim());
    }
}
