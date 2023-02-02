package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Path;

import tasks.TaskList;


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

    // public static void main(String[] args) {
    // Storage storage = new Storage();
    // TaskList taskList = storage.readTaskList();
    // taskList.addTask(new ToDo("1"));
    // taskList.addTask(new ToDo("2"));
    // taskList.addTask(new ToDo("3"));
    // storage.writeTaskList(taskList);
    // TaskList taskList2 = storage.readTaskList();
    // taskList2.printAll();
    // }
}
