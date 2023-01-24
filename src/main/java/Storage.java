import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Storage {
    private String filePath;
    private DateTimeFormatter saveDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList taskList) throws DukeException {
        File file = new File(filePath);
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(taskList.toSaveString(this));
            writer.close();
        } catch (IOException e) {
            throw new CannotWriteFileDukeException();
        }
    }

    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(saveDateTimeFormatter);
    }

    public TaskList load(Parser parser) throws DukeException {
        TaskList taskList = new TaskList();
        File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }
        try {
            List<String> lst = Files.readAllLines(Paths.get(filePath));
            taskList = new TaskList();
            Task task;
            for (String line : lst) {
                task = parser.parseSave(line);
                taskList.add(task);
            }
            return taskList;
        } catch (IOException | IndexOutOfBoundsException | CannotReadFileDukeException e) {
            throw new CannotReadFileDukeException();
        }
    }
}
