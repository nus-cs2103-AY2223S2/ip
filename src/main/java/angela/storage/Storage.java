package angela.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import angela.exception.AngelaException;
import angela.exception.CannotReadFileAngelaException;
import angela.exception.CannotWriteFileAngelaException;
import angela.parser.Parser;
import angela.task.Task;
import angela.task.TaskList;

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
     * @throws AngelaException If the file cannot be opened or written to.
     */
    public void save(TaskList taskList) throws AngelaException {
        File file = new File(filePath);
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(taskList.toSaveString(this));
            writer.close();
        } catch (IOException e) {
            throw new CannotWriteFileAngelaException();
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
     * @throws AngelaException If the file cannot be opened or read.
     */
    public TaskList load(Parser parser) throws AngelaException {
        TaskList taskList = new TaskList();
        File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }
        try {
            List<String> lst = Files.readAllLines(Paths.get(filePath));
            addTaskStringsToTaskList(lst, taskList, parser);
            return taskList;
        } catch (IOException | IndexOutOfBoundsException | CannotReadFileAngelaException e) {
            throw new CannotReadFileAngelaException();
        }
    }

    private void addTaskStringsToTaskList(List<String> taskStrings, TaskList taskList, Parser parser)
            throws CannotReadFileAngelaException {
        Task task;
        for (String line : taskStrings) {
            task = parser.parseSave(line);
            taskList.add(task);
        }
    }
}
