package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FileManager {
    private String filePath;
    private Ui ui;

    public FileManager(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
    }

    public TaskList getFile() {
        try {
            FileInputStream fis = new FileInputStream(this.filePath);
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

    public void saveFile(ArrayList<Task> toDoList) {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toDoList);
            oos.close();
        } catch (IOException e) {
            System.out.println("Please create pandora.txt file");
        }
    }
}
