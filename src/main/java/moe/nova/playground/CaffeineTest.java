package moe.nova.playground;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class CaffeineTest {

    static void main(String[] args) {
        LoadingCache<@NotNull String, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(30L))
                .build(String::length);
        cache.put("test", 4);
        IO.println(cache.get("test"));
        IO.println(cache.get("test1"));

        AsyncLoadingCache<@NotNull String, Integer> cache2 = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(30L))
                .buildAsync(k -> {
                    Thread.sleep(1000L);
                    return k.length();
                });

    }
}
