package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCreate {
    public static File createFile(Path path) {
        try {
            Files.createFile(path);
            File f = new File(path.toString());
            return f;
        } catch (IOException e) {
            System.out.println("Do not have permission to create folder.");
        }

        return null;
    }

    public static void createFolder(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.out.println("Do not have permission to create folder.");
        }
    }
}
