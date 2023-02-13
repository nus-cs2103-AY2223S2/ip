package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import duke.DukeException;
import duke.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * Handles file operations.
 */
public class Storage {

    private final Path filePath;

    /**
     * Constructor for storage.
     *
     * @param filePath Path of file starting from the home directory in Unix.
     */
    public Storage(String filePath) {
        this.filePath = this.formatFilePath(filePath);

        try {
            this.createDirectoriesIfNeeded(this.filePath);
            this.createFileIfNeeded(this.filePath);
        } catch (DukeException ignore) {
            // TODO: find better alternative to ignoring
        }
    }

    private Path formatFilePath(String filePath) {
        String home = System.getProperty("user.home");
        String[] pathComponents = filePath.split("/");

        // Build path
        Path path = Paths.get(home);
        for (String component : pathComponents) {
            path = Paths.get(String.valueOf(path), component);
        }

        return path;
    }

    private void createDirectoriesIfNeeded(Path filePath) throws DukeException {
        try {
            Path directoryPath = filePath.getParent();
            Files.createDirectories(directoryPath);
        } catch (IOException ex) {
            throw new DukeException(ex.toString());
        }
    }

    private void createFileIfNeeded(Path filePath) throws DukeException {
        try {
            Files.createFile(filePath);
        } catch (IOException ex) {
            throw new DukeException(ex.toString());
        }
    }

    /**
     * Load all task saved in files into current session.
     *
     * @return TaskList containing all saved tasks.
     */
    public TaskList loadFile() {
        try {
            String[] allTasks = this.readAllTaskFromFile(this.filePath);
            return this.loadIntoTaskList(allTasks);
        } catch (DukeException e) {
            return new TaskList(new LinkedList<>());
        }
    }

    private String[] readAllTaskFromFile(Path filePath) throws DukeException {
        try {
            return Files.readString(filePath).split("\n");
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

    private TaskList loadIntoTaskList(String[] allTasks) throws DukeException {
        LinkedList<Task> list = new LinkedList<>();
        for (String task : allTasks) {
            switch (task.charAt(0)) {
            case 'T':
                list.add(new ToDo(this.getTaskName(task),
                        this.getIsTaskDone(task)));
                break;
            case 'D':
                list.add(new Deadline(this.getTaskName(task),
                        this.getTaskEndDate(task), this.getIsTaskDone(task)));
                break;
            case 'E':
                list.add(new Event(this.getTaskName(task),
                        this.getTaskStartDate(task), this.getTaskEndDate(task),
                        this.getIsTaskDone(task)));
                break;
            default:
                throw new DukeException(Ui.fileCorruptedMessage);
            }
        }
        return new TaskList(list);
    }

    private String getTaskName(String task) {
        String firstIsStatus = task.substring(task.indexOf("|") + 1);
        String firstIsName = firstIsStatus.substring(firstIsStatus.indexOf("|") + 1);

        if (!firstIsName.contains("|")) {
            return firstIsName;
        }

        return firstIsName.substring(0, firstIsName.indexOf("|"));
    }

    private String getTaskStartDate(String task) {
        String firstIsStatus = task.substring(task.indexOf("|") + 1);
        String firstIsName = firstIsStatus.substring(firstIsStatus.indexOf("|") + 1);
        String firstIsDate = firstIsName.substring(firstIsName.indexOf("|") + 1);

        if (!firstIsDate.contains("|")) {
            return firstIsDate;
        }

        return firstIsDate.substring(0, firstIsDate.indexOf("|"));
    }

    private String getTaskEndDate(String task) {
        return task.substring(task.lastIndexOf('|') + 1);
    }

    private Boolean getIsTaskDone(String task) {
        String firstIsStatus = task.substring(task.indexOf("|") + 1);
        return firstIsStatus.substring(0, firstIsStatus.indexOf("|")).equals("X");
    }

    /**
     * Overwrites the entire file with the current list of task.
     *
     * @param taskList Current list of task.
     */
    public void overwriteFile(TaskList taskList) throws DukeException {
        try {
            Files.writeString(this.filePath, taskList.writeToFile());
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }
}
