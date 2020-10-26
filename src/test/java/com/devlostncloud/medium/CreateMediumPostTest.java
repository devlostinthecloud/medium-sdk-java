package com.devlostncloud.medium;

import com.devlostncloud.medium.model.MediumPost;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.devlostncloud.medium.model.Content.html;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

class CreateMediumPostTest {

    private WireMockServer server;

    @BeforeEach
    void mockMediumAPI() {
        server = new WireMockServer(wireMockConfig().dynamicPort());
        server.start();
    }

    @AfterEach
    void teardown() {
        server.stop();
    }

    @Test
    void createPostWithHtmlContent() {

        server.stubFor(post(urlEqualTo("/v1/users/author12345/posts"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(201)
                        .withBody("{\n" +
                                "  \"data\": {\n" +
                                "    \"id\": \"e6f36a\",\n" +
                                "    \"title\": \"Liverpool FC\",\n" +
                                "    \"authorId\": \"5303d74c64f66366f00cb9b2a94f3251bf5\",\n" +
                                "    \"tags\": [\"football\", \"sport\", \"Liverpool\"],\n" +
                                "    \"url\": \"https://medium.com/@majelbstoat/liverpool-fc-e6f36a\",\n" +
                                "    \"canonicalUrl\": \"http://jamietalbot.com/posts/liverpool-fc\",\n" +
                                "    \"publishStatus\": \"public\",\n" +
                                "    \"publishedAt\": 1442286338435,\n" +
                                "    \"license\": \"all-rights-reserved\",\n" +
                                "    \"licenseUrl\": \"https://medium.com/policy/9db0094a1e0f\"\n" +
                                "  }\n" +
                                "}")));


        MediumPost post = MediumPost.Builder
                .create("How to use Medium API")
                .author("author12345")
                .withContent(html("<h1>Medium API 1</h1>"))
                .baseUrl(server.baseUrl())
                .publish();

        assertThat(post.getData().getId()).isEqualTo("e6f36a");
        assertThat(post.getData().getUrl()).isEqualTo("https://medium.com/@majelbstoat/liverpool-fc-e6f36a");
    }
}
