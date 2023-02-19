package duke.storage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import duke.tasks.TaskList;


/**
 * A class to represent some commonly used methods for reading and writing from Storage.
 */
public class Storage {
    private static final Path STORAGE_PATH = Path.of("src/main/java/data", "Storage.ser");
    private static File storageFile = STORAGE_PATH.toFile();

    private static void createStorageFile() {
        try {
            boolean isParentFileExist = storageFile.getParentFile().exists();
            if (!isParentFileExist) {
                storageFile.getParentFile().mkdirs();
            }
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads from data/Storage.ser and return the saved {@code TaskList} or an empty
     * {@code TaskList} if Storage.ser is not found or corrupted.
     *
     * @return a {@code TaskList} instance
     */
    public static TaskList readTaskList() {
        try {
            FileInputStream fileIn = new FileInputStream(storageFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            TaskList taskList = (TaskList) in.readObject();
            in.close();
            return taskList;
        } catch (IOException e) {
            createStorageFile();
            return new TaskList();
        } catch (ClassNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Writes into data/Storage.ser if it already exists and creates a new Storage.ser before
     * writing if not found.
     *
     * @param taskList the {@code TaskList} instance to be written into Storage.ser
     */
    public static void writeTaskList(TaskList taskList) {
        try {
            FileOutputStream fileOut = new FileOutputStream(storageFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
        } catch (IOException e) {
            createStorageFile();
            throw new Error("storage.ser not found, creating one....");
        }
    }


}
