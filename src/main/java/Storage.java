import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch(IOException e) {
            return null;
        } 
    }

    private static void writeStringToFile(String filePath, String content) throws DukeSaveLoadException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content); 
        } catch(IOException e) {
            throw new DukeSaveLoadException("There's an error writing to save file.");
        }
    }

    public TaskList load() throws DukeSaveLoadException {
        String saveFileContents = Storage.readFile(this.filePath);
        TaskList output = new TaskList();
        if (saveFileContents == null || saveFileContents.isBlank()) {
            return output;
        }
        
        for (String encodedTask : saveFileContents.split("\n")) {
            Task task = Task.loadFromString(encodedTask);
            output.add(task);
        }

        return output;
    }

    public void save(TaskList tasks) throws DukeSaveLoadException {
        Storage.writeStringToFile(this.filePath, tasks.encodeAsString());
    }
}
