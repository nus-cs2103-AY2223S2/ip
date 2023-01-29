import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private File taskList = null;

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<String> load() throws DukeException {
        List<String> loadedFile;
        try {
            File existingList = new File(filepath);
            if (!existingList.exists()) {
                existingList.createNewFile();
            }
            taskList = existingList;
            loadedFile = Files.readAllLines(Paths.get(taskList.getAbsolutePath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return loadedFile;
    }

    public void save(List<String> tasksToSave) throws IOException {
        Files.write(Paths.get(taskList.getAbsolutePath()), tasksToSave);
    }

}
