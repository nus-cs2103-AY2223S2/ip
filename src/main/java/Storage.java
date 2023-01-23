import collections.TaskList;
import utilities.UI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static String getFilePath() {
        Path dataDirPath = Paths.get(System.getProperty("user.dir"), "data");
        if (!Files.exists(dataDirPath)) {
            try {
                Files.createDirectory(dataDirPath);
            } catch (IOException e) {
                UI.output(e.getMessage());
            }
        }
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "task_list.txt");
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                UI.output(e.getMessage());
            }
        }
        return filePath.toString();
    }
    public void saveTaskList(TaskList taskList) {
        try {
            File file = new File(filePath);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            UI.output(e.getMessage());
        }
    }

    public TaskList loadTaskList() {
        TaskList taskList = new TaskList();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                taskList = (TaskList) in.readObject();
                in.close();
                fileIn.close();
            }
        } catch (IOException | ClassNotFoundException e) {}
        return taskList;
    }
}
