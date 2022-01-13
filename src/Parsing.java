import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Parsing {

    public static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл " + path + ". Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
