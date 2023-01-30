import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Storage {
    // For loading from and storing into the file
    Path loadPath;
    Path storePath;
    public Storage(String filePath) {
        String home = System.getProperty("user.home");
        loadPath = java.nio.file.Paths.get(home, filePath);;
        storePath = java.nio.file.Paths.get(System.getProperty("user.dir"), "dukeData.txt");
    }

    public void store(TaskList list) {
        StringBuilder outputConstruct = new StringBuilder();
        for (Task curr : list) {
            outputConstruct.append(curr.toStorageString()).append(System.lineSeparator());
        }
        String finalOut = outputConstruct.toString();
        try {
            if (!java.nio.file.Files.exists(storePath)) {
                java.nio.file.Files.createFile(storePath);
            }
            java.nio.file.Files.write(storePath, finalOut.getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<String> load() throws IOException {
        try {
            return Files.readAllLines(loadPath);
        } catch (IOException e) {
            return Files.readAllLines(storePath);
        }
    }
}
