package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.exception.CannotReadFileDukeException;
import duke.exception.CannotWriteFileDukeException;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class that deals with loading tasks and saving tasks in a file.
 */
public class Storage {
    private String filePath;
    private DateTimeFormatter saveDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Creates a storage instance.
     *
     * @param filePath The file path for the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given task list.
     *
     * @param taskList The task list to be saved.
     * @throws DukeException If the file cannot be opened or written to.
     */
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

    /**
     * Returns a LocalDateTime formatted to a string used for saving.
     *
     * @param dateTime A time.
     * @return The string representation of the given LocalDateTime used for saving.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(saveDateTimeFormatter);
    }

    /**
     * Loads a task list from the save file.
     *
     * @param parser A parser to convert the content of the file to tasks.
     * @return A task list with tasks from the save file.
     * @throws DukeException If the file cannot be opened or read.
     */
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
