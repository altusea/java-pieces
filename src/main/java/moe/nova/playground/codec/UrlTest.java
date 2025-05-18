package moe.nova.playground.codec;

import moe.nova.util.JacksonUtil;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.convert.ConvertUtil;
import org.dromara.hutool.core.net.url.UrlBuilder;
import org.dromara.hutool.core.net.url.UrlDecoder;
import org.dromara.hutool.core.net.url.UrlEncoder;
import org.dromara.hutool.core.net.url.UrlQuery;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class UrlTest {

    public static String stripAnchor(final String s) {
        int anchorIndex = StringUtils.indexOf(s, '#');
        if (anchorIndex != StringUtils.INDEX_NOT_FOUND) {
            return s.substring(0, anchorIndex);
        }
        return s;
    }

    static class UrlHolder {
        String a;

        String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    public static void main(String[] args) {
        String s = "https://www.c.cc/index.html?code=xxx&aa=bb";
        var urlHolder = new UrlHolder();
        urlHolder.setA("axx");
        urlHolder.setB(s);
        var s1 = JacksonUtil.toJson(urlHolder);
        var s2 = UrlEncoder.encodeAll(s1);
        IO.println(s2);
        UrlBuilder urlBuilder0 = UrlBuilder.of("https://www.baidu.com" + "?p10=" + s2);
        var s3 = urlBuilder0.getQuery().get("p10");
        IO.println("s3: " + s3);

        var s4 = UrlDecoder.decode(s2);
        var u1 = JacksonUtil.fromJson(s4, UrlHolder.class);


        IO.println(u1.getB());
        IO.println(UrlEncoder.encodeAll(s));
        IO.println(URLEncoder.encode(s, StandardCharsets.UTF_8));
        printSeparateLine();
        var a = "测试一下子啊a";
        var b = a.getBytes(StandardCharsets.UTF_8);
        var c = UrlBuilder.of("www.baidu.com")
                .addQuery("info", Base64.getUrlEncoder().encodeToString(b))
                .addQuery("another", 1)
                .build();
        var d = UrlBuilder.of(c);
        IO.println(d.getQuery().getQueryMap().get("info"));

        printSeparateLine();
        IO.println(ConvertUtil.toStr("测试"));
        IO.println(URLEncoder.encode("测试", StandardCharsets.UTF_8));
        IO.println(URLEncoder.encode("hello", StandardCharsets.UTF_8));

        UrlQuery urlQuery = UrlQuery.of(UrlQuery.EncodeMode.STRICT);
        urlQuery.add("a", "xxx");
        urlQuery.add("b", "yyy");
        urlQuery.add("a", "zzz");
        IO.println(urlQuery.build(StandardCharsets.UTF_8));

        Map<String, String> params = Map.of("a", "a", "b", "测试");
        IO.println(UrlQuery.of(params).build(StandardCharsets.UTF_8));

        printSeparateLine("test anchor:");
        String anchorUrl = UrlBuilder.of("https://www.baidu.com#toc")
                .addQuery("key1", "a")
                .addQuery("key2", "b")
                .build();
        IO.println(anchorUrl);

        printSeparateLine("test anchor 2:");
        String anchorUrl2 = UrlBuilder.of("https://www.baidu.com#toc")
                .addQuery("key1", "a")
                .addQuery("key2", "b")
                .setFragment("/login")
                .build();
        IO.println(anchorUrl2);

        printSeparateLine();
        UrlBuilder urlBuilder = UrlBuilder.of("https://www.google.com", StandardCharsets.UTF_8);
        urlBuilder.addPath("/path1/path2");
        urlBuilder.addPathSegment("path3");
        urlBuilder.addQuery("order", "ascend");
        urlBuilder.addQuery("name", "张三");
        IO.println(urlBuilder.build());

        UrlBuilder urlBuilder1 = UrlBuilder.of("https://www.google.com?order=ascend", StandardCharsets.UTF_8);
        urlBuilder1.addQuery("pageSize", 10);
        String url = urlBuilder1.build();
        IO.println(url);
        IO.println(UrlEncoder.encodeAll(url, StandardCharsets.UTF_8));

        IO.println(URLEncoder.encode(url, StandardCharsets.UTF_8));

        printSeparateLine();
        String url2 = "https://www.google.com?query=todo&timezone=taipei+qwe";
        IO.println("net:       " + URLEncoder.encode(url2, StandardCharsets.UTF_8));
        IO.println("hutool:    " + UrlEncoder.encodeAll(url2));

        printSeparateLine();
        UrlBuilder urlBuilder3 = UrlBuilder.of("https://www.baidu.com?query=hello");
        urlBuilder3.setFragment("/login");
        String test = urlBuilder3.build();
        IO.println(test);
        IO.println(stripAnchor(test));
        IO.println(stripAnchor("https://www.baidu.com/#/login?query=hello"));
    }
}
