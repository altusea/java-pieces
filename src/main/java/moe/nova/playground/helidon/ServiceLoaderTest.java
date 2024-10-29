package moe.nova.playground.helidon;

import io.helidon.common.HelidonServiceLoader;
import org.slf4j.spi.SLF4JServiceProvider;

import java.util.ServiceLoader;

public class ServiceLoaderTest {

    public static void main(String[] args) {
        ServiceLoader<SLF4JServiceProvider> serviceLoader = ServiceLoader.load(
                SLF4JServiceProvider.class, ClassLoader.getSystemClassLoader());
        var helidonServiceLoader = HelidonServiceLoader.create(serviceLoader);
        helidonServiceLoader.forEach(System.out::println);
    }
}
