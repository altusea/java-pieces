package moe.nova.playground;

import io.avaje.http.client.HttpClient;
import io.avaje.http.client.JsonbBodyAdapter;

import java.net.http.HttpResponse;

public class AvajeHttpTest {

    public static void main(String[] args) {
        try (var client = HttpClient.builder()
                .bodyAdapter(new JsonbBodyAdapter())
                //.bodyAdapter(new JacksonBodyAdapter(new ObjectMapper()))
                //.bodyAdapter(new GsonBodyAdapter(new Gson()))
                .build()) {

            HttpResponse<String> response = client.request()
                    .url("https://www.baidu.com")
                    .GET()
                    .asString();
            System.out.println(response.body());
        }
    }
}
