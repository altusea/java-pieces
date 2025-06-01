package moe.nova.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
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
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.function.Supplier;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class ApacheHttpUtil {

    private static final Supplier<HttpClient> HTTP_CLIENT = StableValue.supplier(
            () -> {
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
                return HttpClients.custom()
                        .setConnectionManager(connManager)
                        .setDefaultRequestConfig(RequestConfig.custom()
                                .setCookieSpec(StandardCookieSpec.IGNORE)
                                .build())
                        .build();
            }
    );

    public static final HttpClientResponseHandler<String> DEFAULT_RESPONSE_HANDLER = new BasicHttpClientResponseHandler();

    private ApacheHttpUtil() {
    }

    private static String executeRequest(ClassicHttpRequest request) {
        try {
            return HTTP_CLIENT.get().execute(request, DEFAULT_RESPONSE_HANDLER);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static String executeRequestWithProxy(ClassicHttpRequest request, HttpHost proxy) {
        try {
            return HTTP_CLIENT.get().execute(proxy, request, DEFAULT_RESPONSE_HANDLER);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static ClassicRequestBuilder basicBuilder(Method method, String url, Map<String, String> headers) {
        ClassicRequestBuilder requestBuilder = ClassicRequestBuilder.create(method.name());
        requestBuilder.setUri(url);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(requestBuilder::setHeader);
        }
        return requestBuilder;
    }

    public static <R> R getForEntity(String url, Map<String, String> headers, Map<String, String> queries, Class<R> responseType) {
        ClassicRequestBuilder requestBuilder = basicBuilder(Method.GET, url, headers);
        if (MapUtils.isNotEmpty(queries)) {
            queries.forEach(requestBuilder::addParameter);
        }

        String bodyStr = executeRequest(requestBuilder.build());
        return JacksonUtil.fromJson(bodyStr, responseType);
    }

    public static <R> R postForEntity(String url, Map<String, String> headers, Object obj, Class<R> responseType) {
        ClassicRequestBuilder requestBuilder = basicBuilder(Method.POST, url, headers);
        requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        requestBuilder.setEntity(new StringEntity(JacksonUtil.toJson(obj), ContentType.APPLICATION_JSON));

        String bodyStr = executeRequest(requestBuilder.build());
        return JacksonUtil.fromJson(bodyStr, responseType);
    }

    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return executeRequest(httpGet);
    }

    public static String getWithProxy(String url, HttpHost proxy) {
        HttpGet httpGet = new HttpGet(url);
        return executeRequestWithProxy(httpGet, proxy);
    }

    static void main(String[] args) throws IOException {
        String url = "https://www.baidu.com";
        IO.println(get(url));

        printSeparateLine();
        HttpHost proxy = new HttpHost("127.0.0.1", 1234);
        IO.println(getWithProxy(url, proxy));

        printSeparateLine();
        var client2 = HttpClients.createDefault();
        var response2 = Request.get("https://www.baidu.com")
                .viaProxy(proxy)
                .execute(client2);
        IO.println(response2.handleResponse(DEFAULT_RESPONSE_HANDLER));
    }
}
