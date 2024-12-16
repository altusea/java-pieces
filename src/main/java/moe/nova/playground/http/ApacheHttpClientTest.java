package moe.nova.playground.http;

import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;

public class ApacheHttpClientTest {

    public static void main(String[] args) {
        try (var client = HttpClients.createDefault()) {
            String body = client.execute(ClassicRequestBuilder.get().setUri("https://www.baidu.com").build(), new BasicHttpClientResponseHandler());
            System.out.println(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
