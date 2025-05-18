package moe.nova.playground;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Executors;

public class JdkHttpTest {

    public static void main(String[] args) {
        try (var httpClient = HttpClient.newBuilder()
                .executor(Executors.newVirtualThreadPerTaskExecutor())
                .build()) {
            List<String> urls = List.of("https://www.baidu.com", "https://www.qq.com/", "https://cn.bing.com/");
            urls.forEach(
                    url -> {
                        try {
                            var future = httpClient.sendAsync(
                                    java.net.http.HttpRequest.newBuilder()
                                            .GET()
                                            .uri(java.net.URI.create(url))
                                            .build(),
                                    HttpResponse.BodyHandlers.ofString()
                            );
                            future.whenComplete((response, throwable) -> {
                                if (throwable != null) {
                                    throwable.printStackTrace();
                                } else {
                                    IO.println(response.body());
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );

        }
    }
}
