package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import  java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Saves and loads tasks in the hard disk.
 */
public class Storage {
    private Path filePath;
    private static final String endOfFilePath = "\\|";

    /**
     * Constructs a new storage file.
     */
    public Storage() {
        this.filePath = openSavedFile();
    }
    private Path openSavedFile() {
        Path filePath = null;
        try {
            String home = System.getProperty("user.home");
            Path directory = Paths.get(home, "Downloads", "CS2103T iP Data");

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            filePath = Paths.get(home, "Downloads", "CS2103T iP Data", "duke.txt");

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException error) {
            System.err.println(error.getMessage());
        } finally {
            return filePath;
        }
    }

    private String[] scanSavedFile(int lineNumber) throws IOException {
        assert lineNumber >= 0: "lineNumber must be non-negative.";
        String lines = Files.readAllLines(filePath).get(lineNumber);
        String[] lineArray = lines.split(endOfFilePath);
        int lengthOfLineArray = lineArray.length;

        for (int i = 0; i < lengthOfLineArray; i++) {
            lineArray[i] = lineArray[i].trim();
        }
        return lineArray;
    }

    /**
     * Convers the file to a <code>TaskList</code> object.
     * @return The TaskList converted from the file.
     * @throws IOException If the programme cannot read the file.
     */
    public TaskList listFile() throws IOException {
        int lineNumber = Math.toIntExact(Files.lines(filePath).count());
        TaskList nameOfTask = new TaskList(new ArrayList<>());

        for (int i = 0; i < lineNumber; i++) {
            String[] str = scanSavedFile(i);
            Task task = convertStringToTask(str);

            if (Integer.parseInt(str[1]) == 1) {
                task.markAsDone();
            } else if (Integer.parseInt(str[1]) == 0) {
                task.unmarkAsDone();
            } else {
                throw new DukeException("Invalid file format.");
            }
            nameOfTask.add(task);
        }
        return nameOfTask;
    }

    private Task convertStringToTask(String[] strings) {
        Task task;
        switch (strings[0]) {
        case "T":
            task = new Todo(strings[2]);
            break;
        case "E":
            String[] date = strings[3].split(" to ");
            LocalDateTime from = convertStringToDateTime(date[0]);
            LocalDateTime to = convertStringToDateTime(date[1]);
            task = new Event(strings[2], from, to);
            break;
        case "D":
            LocalDateTime deadline = convertStringToDateTime(strings[3]);
            task = new Deadline(strings[2], deadline);
            break;
        default:
            throw new DukeException("Invalid type of task.");
        }
        return task;
    }
    private LocalDateTime convertStringToDateTime(String s) {
        String[] dateTime = s.split("T");
        return LocalDateTime.of(LocalDate.parse(dateTime[0]), LocalTime.parse(dateTime[1]));
    }

    /**
     * Saves the entire list of task to the file.
     * @param nameOfTasks A list of tasks to be recorded.
     * @throws IOException If it is unable to save the file.
     */
    public void save(TaskList nameOfTasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath.toString());
        int length = nameOfTasks.numberOfTasks();

        for (int i = 0; i < length; i++) {
            fileWriter.write(nameOfTasks.getTask(i).savedTaskFormat());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Adds a new task to the end of an existing file of tasks.
     * @param task The new task to be added.
     * @throws IOException If it is unable to add the task.
     */
    public void addToStorage(Task task) throws IOException {
        assert task != null: "task is non-empty";
        FileWriter fileWriter = new FileWriter(filePath.toString(), true);
        fileWriter.write(task.savedTaskFormat());
        fileWriter.write(System.lineSeparator());
        fileWriter.close();
    }
}
