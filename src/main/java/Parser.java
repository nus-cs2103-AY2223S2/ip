import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {
    public LocalDateTime parseDateTime(String str) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(str, formatter);
    }

    public Task parseSave(String line) throws CannotReadFileDukeException {
        String[] parsed = line.split("\\|");
        String taskSymbol = parsed[0];
        boolean isTaskDone = Boolean.parseBoolean(parsed[1]);
        String description = parsed[2];
        Task task;
        switch (taskSymbol) {
        case "T":
            task = new ToDoTask(description);
            break;
        case "D":
            task = new DeadlineTask(description, parseDateTime(parsed[3]));
            break;
        case "E":
            task = new EventTask(description, parseDateTime(parsed[3]), parseDateTime(parsed[4]));
            break;
        default:
            throw new CannotReadFileDukeException();
        }
        if (isTaskDone) {
            task.setDone(true);
        }
        return task;
    }
}
