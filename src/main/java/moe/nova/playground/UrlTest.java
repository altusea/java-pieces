package moe.nova.playground;

import cn.hutool.v7.core.net.url.UrlBuilder;
import cn.hutool.v7.core.net.url.UrlUtil;

public class UrlTest {

    static void main() {
        var a = "https://www.baidu.com/open/killSerview?authCode=111111";
        IO.println("path: " + UrlUtil.getPath(a));
        var u = UrlUtil.url(a);
        IO.println("https: " + u.getProtocol().equals("https"));
        IO.println("baidu.com: " + u.getHost().startsWith("www.baidu.com"));

        var b = UrlBuilder.of("https://www.google.com")
                .addQuery("short", "bous")
                .addQuery("invite", null)
                .build();
        IO.println(b);
    }
}
