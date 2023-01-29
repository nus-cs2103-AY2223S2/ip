import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Storage {
    Path path;
    public Storage(String pathStr) {
        String home = System.getProperty("user.dir");
        this.path = java.nio.file.Paths.get(home, pathStr);
    }

}
