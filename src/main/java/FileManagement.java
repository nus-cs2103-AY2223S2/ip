import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class FileManagement {
    private File file;

    public FileManagement() {
        this.file = new File("./data/duke.txt");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void save(List<Task> taskList) {}

    public List<Task> retrieve() {}
}
