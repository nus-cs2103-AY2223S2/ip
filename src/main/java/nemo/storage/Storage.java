package nemo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import nemo.exception.NemoException;
import nemo.task.Task;
import nemo.task.TaskList;

/**
 * Supports operations related to saving and loading TaskList from text file.
 *
 * @author Lian Kok Hai
 */
public class Storage {
    protected final String SAVE_DIR = "./data";
    protected final String SAVE_NAME = "/duke.txt";

    /**
     * Loads a TaskList object with Tasks based on text file in save directory.
     *
     * @param taskList TaskList to be loaded from save.
     * @throws NemoException Thrown when IO error encountered.
     */
    public void loadFromSave(TaskList taskList) throws NemoException {
        File file = new File(SAVE_DIR + SAVE_NAME);
        if (file.exists()) {
            try {
                ArrayList<Task> taskArray = new ArrayList<>();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    taskArray.add(Task.decode(nextLine));
                }
                scanner.close();
                taskList.loadTasks(taskArray);
            } catch (IOException e) {
                throw new NemoException("Shucks! I faced an error loading your previous tasks!");
            }
        }
    }

    /**
     * Saves a TaskList object as text file into save directory.
     * Used when TaskList is updated - mark, unmark, delete, add task.
     *
     * @param taskList TaskList to be saved.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            boolean dataDir = new File(SAVE_DIR).mkdirs();
            File txtFile = new File(SAVE_DIR + SAVE_NAME);
            FileWriter myWriter = new FileWriter(SAVE_DIR + SAVE_NAME);
            myWriter.write(taskList.encode());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
