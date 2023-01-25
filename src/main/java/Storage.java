import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class Storage {
    String home = System.getProperty("user.home");
    java.nio.file.Path path;
    File newFile;

    public Storage(String s) {
        path = java.nio.file.Paths.get(home, "data", s);
        newFile = new File(path.toString());
    }

    public void dirTest() {
        try {
            FileUtils.write(newFile, "Test\ning");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
