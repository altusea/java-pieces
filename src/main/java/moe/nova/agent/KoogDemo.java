package moe.nova.agent;

import ai.koog.agents.core.agent.AIAgent;
import ai.koog.prompt.executor.clients.deepseek.DeepSeekLLMClient;
import ai.koog.prompt.executor.clients.deepseek.DeepSeekModels;
import ai.koog.prompt.executor.llms.MultiLLMPromptExecutor;
import moe.nova.util.DotEnvReader;

public class KoogDemo {

    static void main() {
        // Get the DeepSeek API key from .env file in user home dir
        String apiKey = DotEnvReader.readVal("DEEPSEEK_API_KEY");
        if (apiKey == null) {
            throw new RuntimeException("The API key is not set.");
        }

        // Create an LLM client
        DeepSeekLLMClient deepSeekClient = new DeepSeekLLMClient(apiKey);

        // Create an agent
        AIAgent<String, String> agent = AIAgent.builder()
                // Create a prompt executor using the LLM client
                .promptExecutor(new MultiLLMPromptExecutor(deepSeekClient))
                // Provide a model
                .llmModel(DeepSeekModels.DeepSeekChat)
                .build();

        // Run the agent
        String result = agent.run("Hello! How can you help me?");
        System.out.println(result);
    }
}
