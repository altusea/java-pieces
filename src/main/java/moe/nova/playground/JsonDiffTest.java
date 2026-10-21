package moe.nova.playground;

import com.flipkart.zjsonpatch.DiffFlags;
import com.flipkart.zjsonpatch.Jackson3JsonDiff;
import com.flipkart.zjsonpatch.Jackson3JsonPatch;
import moe.nova.util.JacksonUtil;
import tools.jackson.databind.JsonNode;

import java.util.EnumSet;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class JsonDiffTest {

    static void main() {
        // zjsonpatch demo
        JsonNode source = JacksonUtil.fromJson("""
                {
                  "name": "nova",
                  "age": 18,
                  "tags": ["a", "b"],
                  "address": {"city": "Hangzhou", "zip": "310000"}
                }
                """, JsonNode.class);

        JsonNode target = JacksonUtil.fromJson("""
                {
                  "name": "nova",
                  "age": 19,
                  "tags": ["a", "b", "c"],
                  "address": {"city": "Shanghai", "zip": "310000"}
                }
                """, JsonNode.class);

        // RFC 6902 patch: add /c to tags, replace age, replace city
        JsonNode patch = Jackson3JsonDiff.asJson(source, target);
        IO.println("patch: " + patch.toPrettyString());

        printSeparateLine();

        // ADD_ORIGINAL_VALUE_ON_REPLACE also records what each replace overwrote
        IO.println("patch(verbose): " + Jackson3JsonDiff
                .asJson(source, target, EnumSet.of(DiffFlags.ADD_ORIGINAL_VALUE_ON_REPLACE))
                .toPrettyString());

        printSeparateLine();

        // apply the patch back and check it round-trips
        JsonNode patched = Jackson3JsonPatch.apply(patch, source);
        IO.println("applied: " + patched.toPrettyString());
        IO.println("round-trip ok: " + patched.equals(target));

        printSeparateLine();

        // equality -> empty patch
        IO.println("no diff: " + Jackson3JsonDiff.asJson(target, target));

        printSeparateLine();

        // EMIT_TEST_OPERATIONS guards each mutation (RFC 6902 "test" op)
        IO.println("guard patch: " + Jackson3JsonDiff
                .asJson(source, target, EnumSet.of(DiffFlags.EMIT_TEST_OPERATIONS))
                .toPrettyString());
    }
}
