package duke;

import duke.task.Task;
import java.io.*;
import java.util.ArrayList;
import duke.UI.TextOutput;
import duke.task.TaskList;

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
    public String saveTasks(ArrayList<Task> task) {
        try {
            FileOutputStream file = new FileOutputStream(this.fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(task);
            output.close();
            return TextOutput.makeSucessSaveString();
        } catch (IOException e) {
            return TextOutput.makeUnsuccessSaveString(e);
        }
    }

    /**
     * Retrieves an ArrayList of Tasks from the local hard disk.
     * @return ArrayList of Tasks.
     */
    //@@author CHATGPT
    //Reused from https://chat.openai.com/chat
    // with minor modifications
    public String loadTasks(TaskList list) {
        try {
            FileInputStream file = new FileInputStream(this.fileName);
            ObjectInputStream output = new ObjectInputStream(file);
            ArrayList<Task> task = (ArrayList<Task>)output.readObject();
            output.close();
            list.loadTasks(task);
            return TextOutput.makeSuccessLoadString();
        } catch (IOException | ClassNotFoundException e) {
            ArrayList<Task> newTaskList = new ArrayList<Task>();
            list.loadTasks(newTaskList);
            return TextOutput.makeUnsuccessLoadString();
        }
    }
}
