package duke;

import java.io.*;
import duke.taskType.TaskList;

/**
 * The class used to store and load task list in the local disk.
 */
public class Storage {
    private String filePath;

    /**
     * The constructor to initialize a Storage object.
     *
     * @param filePath the file path to save the task list file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save the task list in the local disk.
     *
     * @param lst the task list to save in the local disk.
     */
    public void save(TaskList lst) {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lst);
            oos.close();
            fos.close();
            System.out.println("Your tasks are saved in the local disk! Roarrrrrrrrrrrrrrrrrr!");
        } catch (Exception e) {
            System.out.println("Roarrrrrrrrrrrrrrrrr! I cannot save your tasks in the local disk!");
            e.printStackTrace();
        }
    }

    /**
     * Load the task list stored in the local disk.
     *
     * @return the task list stored in the local disk.
     */
    public TaskList load() {
        File f = new File(filePath);
        if (f.exists()) {
            TaskList lst = new TaskList();
            try {
                FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                lst = (TaskList) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                System.out.println("Roarrrrrrrrrrrrrrrrrrrr! I cannot load your tasks in the local disk!");
                e.printStackTrace();
            }
            return lst;
        } else {
            System.out.println("Roarrrrrrrrrrrrrrr! You do not have a task list saved in the local disk!");
            System.out.println("Never mind! I will create one for you immediately. Roarrrrrrrrrrrrrrrrrrr!");
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Roarrrrrrrrrrrrrrrrrr! I cannot create the task list file!");
            }
            return new TaskList();
        }
    }
}
