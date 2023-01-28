import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    private Path filePath;

    public Storage(String filePath) {
        String home = System.getProperty("user.home"); // Get home directory
        String[] s = filePath.split("/");

        // Create directories
        this.filePath = Paths.get(home);
        for (int i = 0; i < s.length - 1; i++) {
            this.filePath = Paths.get(String.valueOf(this.filePath), s[i]);

            try {
                Files.createDirectories(this.filePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }

        }

        // Create file
        this.filePath = Paths.get(String.valueOf(this.filePath), s[s.length-1]);
        try {
            Files.createFile(this.filePath); // Create empty file if it does not exist
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }
}
