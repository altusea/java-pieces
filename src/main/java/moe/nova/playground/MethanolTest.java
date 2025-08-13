package moe.nova.playground;

import com.github.mizosoft.methanol.Methanol;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class MethanolTest {

    static void main() {
        var builder = Methanol.newBuilder()
                .userAgent("Will Smith") // Custom User-Agent
                .baseUri("https://api.github.com") // Base URI to resolve requests' URI against
                .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 7890)))
                .defaultHeader("Accept", "application/json") // Default request headers
                .requestTimeout(Duration.ofSeconds(20)) // Default request timeout
                .headersTimeout(Duration.ofSeconds(5)) // Timeout for receiving response headers
                .readTimeout(Duration.ofSeconds(15)) // Timeout for single reads
                .autoAcceptEncoding(true); // Transparent response compression, this is true by default

        // Continue using as a standard HttpClient.Builder.
        try (var client = builder.connectTimeout(Duration.ofSeconds(30)).build()) {
            var response = client.send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("https://api.github.com/users/moe-nova"))
                            .build(), String.class);
            IO.println(response.body());
        } catch (Exception _) {
        }
    }
}
