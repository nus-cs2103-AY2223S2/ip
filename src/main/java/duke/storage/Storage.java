package duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeStorageException;


/**
 * Provides functionality to store and load tasks from "/data/duke.txt"
 */
public class Storage {
    private static final Path DATA_PATH = Paths.get("data");
    private static final Path TASK_LIST_PATH = Paths.get("data", "duke.txt");

    private ArrayList<Task> parseTaskList() throws IOException, DukeStorageException {
        List<String> lines = Files.readAllLines(TASK_LIST_PATH);
        ArrayList<Task> taskList = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            assert parts.length >= 3 : "Malformed task string: " + line;
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task newTask;

            switch (taskType) {
                case "T":
                    newTask = new ToDo(description);
                    break;
                case "D":
                    assert parts.length == 4 : "Malformed deadline task string: " + line;
                    LocalDateTime dueDate = LocalDateTime.parse(parts[3]);
                    newTask = new Deadline(description, dueDate);
                    break;
                case "E":
                    assert parts.length == 5 : "Malformed event task string: " + line;
                    LocalDateTime fromDate = LocalDateTime.parse(parts[3]);
                    LocalDateTime toDate = LocalDateTime.parse(parts[4]);
                    newTask = new Event(description, fromDate, toDate);
                    break;
                default:
                    storeTaskList(new ArrayList<>()); // clear data file
                    throw new DukeStorageException("Oops, something is wrong with the data file! :(\n");
            }

            taskList.add(newTask);
            if (isDone) {
                newTask.setDone();
            }
        }
        return taskList;
    }
    public ArrayList<Task> loadTaskList() {
        try {
            if (!Files.exists(DATA_PATH)) {
                Files.createDirectories(DATA_PATH);
            }
            assert Files.exists(DATA_PATH) : "Data directory does not exist";

            if (!Files.exists(TASK_LIST_PATH)) {
                Files.createFile(TASK_LIST_PATH);
            }
            assert Files.exists(TASK_LIST_PATH) : "Task list file does not exist";

            return parseTaskList();

        } catch (DukeStorageException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void storeTaskList(ArrayList<Task> taskList) throws DukeStorageException {
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(TASK_LIST_PATH);
            for (Task task : taskList) {
                String line = task.getTaskState();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            throw new DukeStorageException("File Error: " + e.getMessage());
        }
    }
}
