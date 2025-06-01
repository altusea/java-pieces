package moe.nova.playground;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

import java.util.List;

public class SensitiveWordTest {

    static void main() {
        List<String> words1 = List.of("中国", "五星红旗");
        List<String> words2 = List.of("中国", "锤子", "镰刀");
        var sensitiveWordInstance = SensitiveWordBs.newInstance().init();
        sensitiveWordInstance.addWord(words1);
        sensitiveWordInstance.addWord(words2);

        String sentence = "中国的国旗是五星红旗。";
        System.out.println(sensitiveWordInstance.replace(sentence));

    }
}
