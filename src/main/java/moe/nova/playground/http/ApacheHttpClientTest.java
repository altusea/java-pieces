package moe.nova.playground.http;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;

public class ApacheHttpClientTest {

    static void main() throws IOException {
        try (var client = HttpClients.createDefault()) {
            String body = client.execute(ClassicRequestBuilder.get().setUri("https://www.baidu.com").build(), new BasicHttpClientResponseHandler());
            IO.println(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PoolingHttpClientConnectionManager connManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setTlsSocketStrategy(new DefaultClientTlsStrategy(
                        SSLContexts.createDefault(),
                        NoopHostnameVerifier.INSTANCE))
                .setDefaultSocketConfig(SocketConfig.custom()
                        .setSoTimeout(Timeout.ofMinutes(1))
                        .build())
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.LAX)
                .setConnPoolPolicy(PoolReusePolicy.FIFO)
                .setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setSocketTimeout(Timeout.ofMinutes(1))
                        .setConnectTimeout(Timeout.ofMinutes(1))
                        .setTimeToLive(TimeValue.ofMinutes(10))
                        .build())
                .build();
        var client = HttpClients.custom()
                .setConnectionManager(connManager)
                .setConnectionManagerShared(true) // related to client.close()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(StandardCookieSpec.IGNORE)
                        .build())
                .build();
        client.close();
        client.execute(ClassicRequestBuilder.get().setUri("https://www.baidu.com").build(), new BasicHttpClientResponseHandler());
        IO.println(connManager.isClosed());

        var response = Request.get("https://www.baidu.com")
                .execute();
        IO.println(response.handleResponse(new BasicHttpClientResponseHandler()));
    }
}
