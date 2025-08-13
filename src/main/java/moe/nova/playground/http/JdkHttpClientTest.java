package moe.nova.playground.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JdkHttpClientTest {

    static void main() {
        try (var client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://www.baidu.com"))
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            IO.println(response.body());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
