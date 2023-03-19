package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The class that store the data.
 */
public class Storage {

    private static String dataDir;
    private static String dataPath;

    /**
     * Constructs this class.
     */
    public Storage() {
        dataDir = System.getProperty("user.dir") + File.separator + "data";
        dataPath = dataDir + File.separator + "duke.txt";
    }

    /**
     * Loads the data.
     *
     * @return the task list which is saved from last access.
     */
    public TaskList load() {
        File dir = new File(dataDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File duke = new File(dataPath);
        if (!duke.exists()) {
            return new TaskList();
        }
        TaskList todo = null;
        FileInputStream f = null;
        ObjectInputStream f1 = null;
        try {
            f = new FileInputStream(dataPath);
            f1 = new ObjectInputStream(f);
            todo = (TaskList) f1.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                f1.close();
                f.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
        return todo;
    }

    /**
     * Saves the tasks list into the storage file.
     *
     * @param todo
     */
    public void save(TaskList todo) {
        FileOutputStream f1 = null;
        ObjectOutputStream f2 = null;
        try {
            f1 = new FileOutputStream(dataPath);
            f2 = new ObjectOutputStream(f1);
            f2.writeObject(todo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                f2.close();
                f1.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }
}
