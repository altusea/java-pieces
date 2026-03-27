package moe.nova.agent;

import ai.koog.agents.core.agent.AIAgent;
import ai.koog.agents.planner.AIAgentPlannerStrategy;
import ai.koog.prompt.executor.clients.deepseek.DeepSeekModels;
import ai.koog.prompt.executor.model.PromptExecutor;
import moe.nova.util.DotEnvReader;

public class KoogPanner {

    static void main() {
        // Create the planner strategy with LLM-based planner
        AIAgentPlannerStrategy<String, String, ?> strategy = AIAgentPlannerStrategy.builder("simple-planner")
                .llmBasedPlanner()
                .build();

        // Create the DeepSeek executor
        var promptExecutor = PromptExecutor.builder()
                .deepseek(DotEnvReader.readVal("DEEPSEEK_API_KEY"))
                .build();

        // Create the planner agent using AIAgent builder
        AIAgent<String, String> agent = AIAgent.builder()
                .plannerStrategy(strategy)
                .promptExecutor(promptExecutor)
                .llmModel(DeepSeekModels.DeepSeekChat)
                .systemPrompt("You are a helpful planning assistant.")
                .maxIterations(50)
                .build();

        // Run the agent with a task
        String result = agent.run("Create a plan to organize a team meeting");
        System.out.println(result);
    }
}
