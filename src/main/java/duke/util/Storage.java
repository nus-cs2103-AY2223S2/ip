package duke.util;

import duke.exception.DukeException;
import duke.exception.ERROR;
import duke.task.Task;
import duke.task.TaskList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static Storage createStorage(String filePath) {
        String extension = getFileExtension(filePath);
        Storage storage = null;

        switch (extension) {
            case "txt":
                storage = new StorageTextFile(filePath);
                break;
            case "csv":
                storage = new StorageCsvFile(filePath);
                break;
        }

        return storage;
    }

    private static String getFileExtension(String filePath) {
        String extension = "";
        int index = filePath.lastIndexOf(".");
        if (index > 0) {
            extension = filePath.substring(index + 1);
        }
        return extension;
    }

    public String getFilePath() {
        return filePath;
    }

    public abstract TaskList load() throws DukeException;

    public abstract boolean save(TaskList list);
}
