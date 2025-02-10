package moe.nova.playground;

import org.dromara.hutool.core.net.url.UrlUtil;

public class UrlTest {

    public static void main(String[] args) {
        var a = "https://www.baidu.com/open/killSerview?authCode=111111";
        System.out.println(UrlUtil.getPath(a));
        var u = UrlUtil.url(a);
        System.out.println(u.getProtocol().equals("https"));
        System.out.println(u.getHost().startsWith("www.baidu.com"));
    }
}
