package com.devlostncloud.medium;

import kong.unirest.json.JSONObject;
import java.util.Map;

import static java.lang.String.format;
import static kong.unirest.Unirest.post;

class PostPublisher {
    private static final String MEDIUM_API_BASE_URL = "https://api.medium.com";
    private static final String MEDIUM_API_POSTS_PATH_FORMAT = "/v1/users/%s/posts";
    private final String baseUrl;

    static PostPublisher mediumPublisher() {
        return new PostPublisher(MEDIUM_API_BASE_URL);
    }

    PostPublisher(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    Post publish(String authorId, Map<String, Object> postParams) {
        return post(format(baseUrl + MEDIUM_API_POSTS_PATH_FORMAT, authorId))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Accept-Charset", "utf-8")
                .body(new JSONObject(postParams))
                .asObject(Post.class)
                .getBody();
    }
}
