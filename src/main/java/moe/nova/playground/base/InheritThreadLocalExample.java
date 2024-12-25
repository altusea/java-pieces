package moe.nova.playground.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InheritThreadLocalExample {

    private static final Logger log = LoggerFactory.getLogger(InheritThreadLocalExample.class);

    // 创建一个 InheritableThreadLocal 对象
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        // 在父线程中设置数据
        threadLocal.set("Hello, World!");

        // 创建一个子线程
        Thread childThread = new Thread(() -> {
            // 在子线程中获取父线程的数据
            String data = threadLocal.get();
            System.out.println("Child Thread - Data: " + data);
        });

        // 启动子线程
        childThread.start();

        try {
            // 等待子线程执行完毕
            childThread.join();
        } catch (InterruptedException e) {
            log.error("Interrupted", e);
        }

        // 在父线程中获取数据
        String data = threadLocal.get();
        System.out.println("Main Thread - Data: " + data);
    }
}
