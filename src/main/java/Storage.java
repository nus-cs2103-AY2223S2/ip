import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public boolean isExistFile() {
        return new java.io.File(filePath).exists();
    }

    // Idea for the following code snippet to create a file is referenced from:
    // https://stackoverflow.com/questions/6142901/how-to-create-a-file-in-a-directory-in-java
    public File load() {
        if (!isExistFile()) {
            Path desiredPath = Paths.get(filePath);
            try {
                Files.createDirectories(desiredPath.getParent());
            } catch (IOException err) {
                System.out.println(err);
            }

            try {
                Files.createFile(desiredPath);
            } catch (FileAlreadyExistsException err) {
                System.err.println("already exists: " + err.getMessage());
            } catch (IOException err) {
                System.out.println(err);
            }

            try {
                Files.write(Paths.get(filePath), "0".getBytes());
            } catch (IOException err) {
                System.out.println(err);
            }

            File createdFile = new File(filePath);
            return createdFile;
        } else {
            File savedFile = new File(filePath);
            return savedFile;
        }
    }
}
