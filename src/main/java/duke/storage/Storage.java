package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Manages task operation that involves underlying file storage.
 */
public class Storage {
    private static final String delimiter = "\\|";
    private Path filePath;

    /**
     * Constructs a new storage.
     */
    public Storage() {
        this.filePath = openDataFile();
    }

    private Path openDataFile() {
        Path filePath = null;
        try {
            String home = System.getProperty("user.home");
            Path pathToDir = Paths.get(home, "data");

            if (!Files.exists(pathToDir)) {
                Files.createDirectories(pathToDir);
            }
            filePath = Paths.get(home, "data", "duke.txt");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            return filePath;
        }
    }

    private String[] readFromDataFile(int lineNumber) throws IOException {
        assert lineNumber >= 0 : "lineNumber must be non-negative";
        String line = Files.readAllLines(filePath).get(lineNumber);
        String[] lineArray = line.split(delimiter);
        int len = lineArray.length;
        for (int i = 0; i < len; i++) {
            lineArray[i] = lineArray[i].trim();
        }
        return lineArray;
    }

    /**
     * Converts the file content to a <code>TaskList</code> object.
     *
     * @return The TaskList converted from the file.
     * @throws IOException If reading from the file fails.
     */
    public TaskList loadFileToTaskList() throws IOException {
        int lineNumber = Math.toIntExact(Files.lines(filePath).count());
        TaskList tasks = new TaskList(new ArrayList<>());
        for (int i = 0; i < lineNumber; i++) {
            String[] s = readFromDataFile(i);
            Task task = convertStringToTask(s);
            if (Integer.parseInt(s[1]) == 1) {
                task.markAsDone();
            } else if (Integer.parseInt(s[1]) == 0) {
                task.markAsNotDone();
            } else {
                throw new DukeException("Invalid file format");
            }
            tasks.add(task);
        }
        return tasks;
    }

    private Task convertStringToTask(String[] s) {
        Task task;
        switch (s[0]) {
        case "T":
            task = new Todo(s[2]);
            break;
        case "D":
            LocalDateTime t = convertStringToDateTime(s[3]);
            task = new Deadline(s[2], t);
            break;
        case "E":
            String[] date = s[3].split(" to ");
            LocalDateTime start = convertStringToDateTime(date[0]);
            LocalDateTime end = convertStringToDateTime(date[1]);
            task = new Event(s[2], start, end);
            break;
        default:
            throw new DukeException("Invalid type of task");
        }
        return task;
    }

    private LocalDateTime convertStringToDateTime(String s) {
        String[] arr = s.split("T");
        return LocalDateTime.of(LocalDate.parse(arr[0]), LocalTime.parse(arr[1]));
    }

    /**
     * Writes the whole list of tasks to the file.
     *
     * @param tasks A list of tasks to be recorded.
     * @throws IOException If writing to the file fails.
     */
    public void writeTasksToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath.toString());
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            fw.write(tasks.get(i).taskInFileFormat());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Appends a new task to the end of file.
     *
     * @param task The new task to be appended.
     * @throws IOException If appending the task to the file fails.
     */
    public void addTaskToFile(Task task) throws IOException {
        assert task != null : "task is non-empty";
        FileWriter fw = new FileWriter(filePath.toString(), true);
        fw.write(task.taskInFileFormat());
        fw.write(System.lineSeparator());
        fw.close();
    }
}
