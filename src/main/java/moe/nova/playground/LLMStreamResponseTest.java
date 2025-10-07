package moe.nova.playground;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LLMStreamResponseTest {

    static void main() throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            Dotenv env = Dotenv.configure()
                    .directory(FileUtils.getUserDirectory().getPath())
                    .load();
            String deepseekApiKey = env.get("DEEPSEEK_API_KEY");
            System.out.println("deepseekApiKey: " + deepseekApiKey);

            HttpPost post = new HttpPost("https://api.deepseek.com/chat/completions");
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + deepseekApiKey);

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
            post.setEntity(new StringEntity(bodyStr));

            var response = client.execute(post);
            HttpEntity entity = response.getEntity();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
    }
}
