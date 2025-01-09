package moe.nova.playground;

import com.github.mizosoft.methanol.Methanol;

import java.time.Duration;

public class MethanolTest {

    public static void main(String[] args) {
        var builder = Methanol.newBuilder()
                .userAgent("Will Smith") // Custom User-Agent
                .baseUri("https://api.github.com") // Base URI to resolve requests' URI against
                .defaultHeader("Accept", "application/json") // Default request headers
                .requestTimeout(Duration.ofSeconds(20)) // Default request timeout
                .headersTimeout(Duration.ofSeconds(5)) // Timeout for receiving response headers
                .readTimeout(Duration.ofSeconds(5)) // Timeout for single reads
                .autoAcceptEncoding(true); // Transparent response compression, this is true by default

        // Continue using as a standard HttpClient.Builder.
        try (var client = builder.connectTimeout(Duration.ofSeconds(30)).build()) {
        }
    }
}
