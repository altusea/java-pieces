package moe.nova.playground;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.internal.RealEventSource;
import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CountDownLatch;

public class LLMStreamResponseOkHttpTest {

    static final MediaType JSON = MediaType.parse("application/json");

    static void main() throws Exception {
        Dotenv env = Dotenv.configure()
                .directory(FileUtils.getUserDirectory().getPath())
                .load();
        String deepseekApiKey = env.get("DEEPSEEK_API_KEY");
        System.out.println("deepseekApiKey: " + deepseekApiKey);

        String bodyStr = """
                {
                    "model": "deepseek-chat",
                    "messages": [
                        {"role": "system", "content": "You are a helpful assistant."},
                        {"role": "user", "content": "Hello! 帮我写一首诗."}
                    ],
                    "stream": true
                }
                """;
        RequestBody requestBody = RequestBody.create(bodyStr, JSON);

        Request request = new Request.Builder()
                .url("https://api.deepseek.com/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + deepseekApiKey)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        System.out.println("Starting SSE request using RealEventSource...");

        // Create a CountDownLatch to track completion
        CountDownLatch latch = new CountDownLatch(1);

        // Create EventSource listener
        EventSourceListener listener = new EventSourceListener() {
            @Override
            public void onOpen(@NonNull EventSource eventSource, Response response) {
                System.out.println("SSE connection opened: " + response.code());
            }

            @Override
            public void onEvent(@NonNull EventSource eventSource, String id, String type, @NonNull String data) {
                System.out.println("SSE Event - ID: " + id + ", Type: " + type);
                System.out.println(data);
            }

            @Override
            public void onClosed(@NonNull EventSource eventSource) {
                System.out.println("SSE connection closed");
                latch.countDown();
            }

            @Override
            public void onFailure(@NonNull EventSource eventSource, Throwable t, Response response) {
                System.err.println("SSE connection failed: " + t.getMessage());
                if (response != null) {
                    System.err.println("Response code: " + response.code());
                }
                latch.countDown();
            }
        };

        // Create RealEventSource
        RealEventSource eventSource = new RealEventSource(request, listener);

        // Connect to the SSE stream
        eventSource.connect(client);

        // Wait for completion
        try {
            latch.await();
            System.out.println("Stream processing completed.");
        } catch (InterruptedException e) {
            System.err.println("Stream processing interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
