package treebot.interfaces;

import treebot.tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents an interface for data persistence.
 */
public interface IStorage {

    /**
     * Loads the task from the text file by parsing them into <code>Task</code> objects.
     * @return
     * @throws FileNotFoundException
     */
    ArrayList<Task> loadTasks() throws FileNotFoundException;

    /**
     * Saves current state of the task list to text file.
     * @param taskListState
     * @throws IOException
     */
    void saveTasks(ArrayList<Task> taskListState) throws IOException;


}
