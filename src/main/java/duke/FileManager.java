package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.nio.file.Paths;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

/**
 * Solution below adapted from https://github.com/jamieeeleow/ip/blob/master/src/main/java/leo/storage/Storage.java
 */
public class FileManager {
    private String saveFilePath;
    private Ui ui;

    public FileManager(String filePath) {
        this.saveFilePath = filePath;
        this.ui = new Ui();
    }

    /**
     * Returns TaskList object containing the saved ArrayList file,
     * else return a new TaskList object with a new ArrayList.
     * @return TaskList Object
     */
    public TaskList getFile() {
        String root = Paths.get("").toAbsolutePath().toString();
        String relativeFilePath = Paths.get(root, this.saveFilePath).toString();
        try {
            FileInputStream fis = new FileInputStream(relativeFilePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> toDoList = (ArrayList<Task>) ois.readObject();
            System.out.println(this.ui.savedFileFound());
            TaskList taskList = new TaskList(toDoList);
            return taskList;
        } catch (IOException e) {
            System.out.println(this.ui.savedFileNotFound());
            ArrayList<Task> toDoList = new ArrayList<Task>();
            TaskList taskList = new TaskList(toDoList);
            return taskList;
        } catch (ClassNotFoundException e) {
            System.out.println(this.ui.savedFileNotFound());
            ArrayList<Task> toDoList = new ArrayList<Task>();
            TaskList taskList = new TaskList(toDoList);
            return taskList;
        }
    }

    /**
     * Returns void.
     * <p>
     * Takes in an ArrayList of Task and save it in the specified file path.
     * <p>
     * File type should be specified in the filepath.
     * @param toDoList
     */
    public void saveFile(ArrayList<Task> toDoList) {
        String root = Paths.get("").toAbsolutePath().toString();
        String relativeFilePath = Paths.get(root, this.saveFilePath).toString();

        //creates the directory
        new File(relativeFilePath).getParentFile().mkdirs();
        //check if saved file exist
        if (!new File(relativeFilePath).exists()) {
            try {
                new File(relativeFilePath).createNewFile();
            } catch (IOException e) {
                System.out.println("File not created");
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(relativeFilePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toDoList);
            oos.close();
            System.out.println(this.ui.fileSaved());
        } catch (IOException e) {
            System.out.println("Please create pandora.txt file");
        }
    }
}
