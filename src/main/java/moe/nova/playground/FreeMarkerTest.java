package moe.nova.playground;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class FreeMarkerTest {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDefaultEncoding("UTF-8");

        try (Reader reader = new StringReader("R u kidding ${name}?");
             Writer writer = new StringWriter()) {
            Template template = new Template("a", reader, cfg);

            Map<String, String> map = Map.of("name", "Alice");
            template.process(map, writer);

            // 输出结果
            System.out.println(writer);
        }
    }
}
