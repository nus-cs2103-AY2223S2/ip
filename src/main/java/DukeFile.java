import java.io.File;
import java.io.IOException;

public class DukeFile {

    private static File f;
    private static String filePath;

    public DukeFile(String filePath) {
        DukeFile.filePath = filePath;
    }

    public static void main(String[] args) {
        try {
            f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error:" + e.toString());
        }
    }

    public static File getFile() {
        return f;
    }
}
