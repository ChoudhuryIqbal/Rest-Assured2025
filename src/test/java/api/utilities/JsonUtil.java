package api.utilities;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {

    public static String loadJson(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static String updateJsonTemplate(String template, String[][] replacements) {
        for (String[] pair : replacements) {
            template = template.replace(pair[0], pair[1]);
        }
        return template;
    }
}
