package duke;

import duke.task.Tasks;
import java.io.*;
import java.util.ArrayList;


/**
 * This class represents the local storage of task list.
 */
public class Storage {

    String fileName = "";

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method stores an ArrayList of Tasks in local hard disk.
     * @param task
     */
    //adapted from CHATGPT
    public void saveTasks(ArrayList<Tasks> task) {
        try {
            FileOutputStream file = new FileOutputStream(this.fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(task);
            output.close();
        } catch (IOException e) {
            System.out.println("file error");
        }
    }

    /**
     * This method retrieves an ArrayList of Tasks from the local hard disk.
     * @return ArrayList of Tasks.
     */
    //adapted from CHATGPT
    public ArrayList<Tasks> loadTasks() {
        try {
            FileInputStream file = new FileInputStream(this.fileName);
            ObjectInputStream output = new ObjectInputStream(file);
            ArrayList<Tasks> task = (ArrayList<Tasks>)output.readObject();
            output.close();
            return task;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<Tasks>();
        }
    }
}
