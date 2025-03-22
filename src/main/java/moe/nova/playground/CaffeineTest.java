package moe.nova.playground;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.time.Duration;

public class CaffeineTest {

    public static void main(String[] args) {
        LoadingCache<String, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(30L))
                .build(String::length);
        cache.put("test", 4);
        System.out.println(cache.get("test"));
        System.out.println(cache.get("test1"));

        AsyncLoadingCache<String, Integer> cache2 = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(30L))
                .buildAsync(k -> {
                    Thread.sleep(1000L);
                    return k.length();
                });

    }
}
