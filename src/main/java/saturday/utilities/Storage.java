package saturday.utilities;

import saturday.collections.TaskList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * A utility class to handle loading and saving of TaskList object to a file
 */
public class Storage {
    private static String filePath;

    /**
     * Creates a new Storage object with the given file path.
     *
     * @param filePath The file path where the TaskList will be saved to and loaded from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path where the TaskList will be saved to and loaded from.
     *
     * @return The file path where the TaskList will be saved to and loaded from
     */
    public static String getFilePath() {
        Path dataDirPath = Paths.get(System.getProperty("user.dir"), "data");
        if (!Files.exists(dataDirPath)) {
            try {
                Files.createDirectory(dataDirPath);
            } catch (IOException e) {
                Ui.output(e.getMessage());
            }
        }
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "task_list.txt");
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                Ui.output(e.getMessage());
            }
        }
        return filePath.toString();
    }

    /**
     * This method saves the taskList to the file specified in the filePath attribute.
     *
     * @param taskList TaskList to write to file
     */
    public void saveTaskList(TaskList taskList) {
        try {
            File file = new File(filePath);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            Ui.output(e.getMessage());
        }
    }

    /**
     * This method loads the taskList from the file specified in the filePath attribute.
     *
     * @return TaskList read from file
     */
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
