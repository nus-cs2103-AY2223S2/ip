package storage;

import tasks.TaskList;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;

public class Storage {
    private static final Path STORAGE_PATH = Path.of("data", "duke.txt");

    public Storage() {}

    public static Path getStoragePath() {
        return STORAGE_PATH;
    }

    public TaskList getTaskList() {
        return new TaskList();
    }

    public void writeTasks() {

    }
}
