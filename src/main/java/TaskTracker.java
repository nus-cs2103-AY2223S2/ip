import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskTracker {
    private static final String SAVE_DIR = "data";
    private static final String SAVE_NAME = "tasks.txt";

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public int GetNumTasks() {
        return tasks.size();
    }

    public Task GetTask(int index) throws TaskNotFoundException {
        if (index < 0 || tasks.size() < index + 1) {
            throw new TaskNotFoundException("Task " + (index + 1) + " not found!");
        }

        return tasks.get(index);
    }

    public Task AddTodo(String taskDescription) throws DukeException {
        Task t = new Task(taskDescription);
        tasks.add(t);
        return t;
    }

    public Task AddDeadline(String taskDescription, String endDate) throws DukeException {
        Task t = new Deadline(taskDescription, endDate);
        tasks.add(t);
        return t;
    }

    public Task AddEvent(String taskDescription, String startDate, String endDate) throws DukeException {
        Task t = new Event(taskDescription, startDate, endDate);
        tasks.add(t);
        return t;
    }

    public Task DeleteTask(int index) throws DukeException {
        Task toDelete = GetTask(index);
        tasks.remove(index);
        return toDelete;
    }

    public Task MarkTask(int index) throws DukeException {
        return MarkUnmarkTask(index, true);
    }

    public Task UnmarkTask(int index) throws TaskNotFoundException {
        return MarkUnmarkTask(index, false);
    }

    public Task MarkUnmarkTask(int index, boolean isMarked) throws TaskNotFoundException {
        Task task = GetTask(index);
        if (isMarked) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }

    public String ListTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < GetNumTasks(); i++) {
            sb.append("\n\t").append(i + 1).append(". ").append(tasks.get(i));
        }
        return sb.toString();
    }

    public void SaveAllTasks() throws TaskSaveException {
        try {
            Path savePath = Paths.get(SAVE_DIR, SAVE_NAME);
            Files.createDirectories(Paths.get(SAVE_DIR));
            if (!Files.exists(savePath)) {
                Files.createFile(savePath);
            }
            FileWriter fw = new FileWriter(savePath.toFile(), false);
            fw.write(SerializeTasks());
            fw.close();
        } catch (IOException e) {
            throw new TaskSaveException("Tasks not saved!");
        }
    }

    private String SerializeTasks() {
        StringBuilder serialized  = new StringBuilder();
        for (Task task : tasks) {
            serialized.append(task.serialize()).append("\n");
        }
        return serialized.toString();
    }

    public void LoadTasks() throws TaskSaveException {
        Path savePath = Paths.get(SAVE_DIR, SAVE_NAME);
        if (!Files.exists(savePath)) {
            return;
        }

        if (tasks.size() > 0) {
            throw new TaskSaveException("Unable to load! Tasks already loaded!");
        }

        try {
            File saveF = savePath.toFile();
            Scanner s = new Scanner(saveF);
            while (s.hasNext()) {
                tasks.add(parseTaskString(s.nextLine()));
            }
            s.close();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
            throw new TaskSaveException("Unable to load tasks!");
        } catch (DukeException e) {
            throw new TaskSaveException("Unable to load tasks! Tasks save file may be corrupted!");
        }
    }

    private Task parseTaskString(String taskStr) throws DukeException {
        String taskType = taskStr.split("|")[0];
        switch (taskType) {
        case "T":
            return Task.deserialize(taskStr);
        case "D":
            return Deadline.deserialize(taskStr);
        case "E":
            return Event.deserialize(taskStr);
        default:
            return null;
        }
    }
}
