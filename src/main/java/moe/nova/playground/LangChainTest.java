package moe.nova.playground;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.openai.OpenAiChatModel;
import moe.nova.util.DotEnvReader;

import java.util.List;

public class LangChainTest {

    static void main(String[] args) {
        String deepseekApiKey = DotEnvReader.readVal("DEEPSEEK_API_KEY");
        System.out.println("deepseekApiKey: " + deepseekApiKey);
        var chatModel = OpenAiChatModel.builder()
                .baseUrl("https://api.deepseek.com")
                .apiKey(deepseekApiKey)
                .build();
        var chatResponse = chatModel.chat(ChatRequest.builder()
                .modelName("deepseek-chat")
                .messages(List.of(
                        new SystemMessage("You are a helpful assistant."),
                        new UserMessage("Hello! 帮我写一首诗.")
                ))
                .build());
        System.out.println(chatResponse.aiMessage().text());
        System.out.println(chatResponse.finishReason());
    }
}
