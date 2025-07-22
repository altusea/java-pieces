package moe.nova.playground;

import cn.hutool.v7.core.net.url.UrlUtil;

public class UrlTest {

    static void main(String[] args) {
        var a = "https://www.baidu.com/open/killSerview?authCode=111111";
        IO.println(UrlUtil.getPath(a));
        var u = UrlUtil.url(a);
        IO.println(u.getProtocol().equals("https"));
        IO.println(u.getHost().startsWith("www.baidu.com"));
    }
}
