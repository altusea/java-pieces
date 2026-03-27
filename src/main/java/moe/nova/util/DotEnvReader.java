package moe.nova.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;

public class DotEnvReader {

    public static String readVal(String key) {
        Dotenv env = Dotenv.configure()
                .directory(FileUtils.getUserDirectory().getPath())
                .load();
        return env.get(key);
    }
}
