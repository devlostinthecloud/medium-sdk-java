package com.devlostncloud.medium;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.devlostncloud.medium.Content.html;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
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
    void createPostWithRequiredHtmlContent() {

        server.stubFor(post(urlEqualTo("/v1/users/author12345/posts"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("Accept-Charset", equalTo("utf-8"))
                .withRequestBody(equalToJson("{\n" +
                        "  \"title\": \"How to use Medium API\",\n" +
                        "  \"contentFormat\": \"html\",\n" +
                        "  \"content\": \"<h1>How to use Medium API</h1>\"\n" +
                        "}", true, false))
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

        MediumPost post = MediumPost.builder()
                .title("How to use Medium API")
                .author("author12345")
                .content(html("<h1>How to use Medium API</h1>"))
                .baseUrl(server.baseUrl())
                .publish();

        assertThat(post.getId()).isEqualTo("e6f36a");
        assertThat(post.getUrl()).isEqualTo("https://medium.com/@majelbstoat/liverpool-fc-e6f36a");
    }
}
