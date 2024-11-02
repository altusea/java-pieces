package moe.nova.util;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;

public class ApacheHttpClientFactory {

    public static CloseableHttpClient createProxyClient(HttpHost proxy) {
        var proxyRoutePlanner = new DefaultProxyRoutePlanner(proxy);
        return HttpClients.custom()
                .setRoutePlanner(proxyRoutePlanner)
                .build();
    }

    public static void main(String[] args) throws IOException {
        ClassicHttpRequest request = ClassicRequestBuilder.get("https://www.google.com").build();
        try (var httpClient = createProxyClient(new HttpHost("127.0.0.1", 7890))) {
            var response = httpClient.execute(request, ApacheHttpUtil.DEFAULT_RESPONSE_HANDLER);
            System.out.println(response);
        }
    }
}
