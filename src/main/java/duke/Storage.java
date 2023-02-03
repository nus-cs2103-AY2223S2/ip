package duke;

import duke.task.Tasks;
import java.io.*;
import java.util.ArrayList;
import duke.UI;


/**
 * Represents the local storage of task list.
 */
public class Storage {

    String fileName = "";

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Stores an ArrayList of Tasks in local hard disk.
     * @param task The ArrayList of Tasks to store in the local hard disk.
     */
    //Credits: Adapted from CHATGPT
    public void saveTasks(ArrayList<Tasks> task) {
        try {
            FileOutputStream file = new FileOutputStream(this.fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(task);
            output.close();
            UI.successfulSaveTask();
        } catch (IOException e) {
            UI.unsuccessulSaveTask(e);
        }
    }

    /**
     * Retrieves an ArrayList of Tasks from the local hard disk.
     * @return ArrayList of Tasks.
     */
    //Credits: Adapted from CHATGPT
    public ArrayList<Tasks> loadTasks() {
        try {
            FileInputStream file = new FileInputStream(this.fileName);
            ObjectInputStream output = new ObjectInputStream(file);
            ArrayList<Tasks> task = (ArrayList<Tasks>)output.readObject();
            output.close();
            UI.successfulLoadTask();
            return task;
        } catch (IOException | ClassNotFoundException e) {
            UI.unsucessfulLoadTask();
            return new ArrayList<Tasks>();
        }
    }
}
