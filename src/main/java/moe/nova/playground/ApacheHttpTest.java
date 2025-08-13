package moe.nova.playground;

import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.message.StatusLine;

import java.util.concurrent.Future;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class ApacheHttpTest {

    static void main() {
        try (var client = HttpClients.createDefault()) {
            var bodyStr = Request.get("https://www.baidu.com")
                    .viaProxy("http://127.0.0.1:7890")
                    .execute(client)
                    .handleResponse(new BasicHttpClientResponseHandler());
            IO.println(bodyStr);


        } catch (Exception _) {
        }

        printSeparateLine();

        try (var client = HttpAsyncClients.createDefault()) {
            final SimpleHttpRequest request = SimpleRequestBuilder.get("https://www.baidu.com")
                    .build();
            IO.println("Executing request " + request);
            final Future<SimpleHttpResponse> future = client.execute(
                    SimpleRequestProducer.create(request),
                    SimpleResponseConsumer.create(),
                    new FutureCallback<>() {

                        @Override
                        public void completed(final SimpleHttpResponse response) {
                            IO.println(request + "->" + new StatusLine(response));
                            IO.println(response.getBody());
                        }

                        @Override
                        public void failed(final Exception ex) {
                            IO.println(request + "->" + ex);
                        }

                        @Override
                        public void cancelled() {
                            IO.println(request + " cancelled");
                        }

                    });
            future.get();
        } catch (Exception _) {
        }
    }
}
