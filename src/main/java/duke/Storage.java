package duke;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Manages storage of tasklists into files
 */
public class Storage {
    private final String savePath;

    Storage(String savePath) {
        this.savePath = savePath;
    }

    /**
     * Stores the parameterized TaskList as a serialized file
     *
     * @param list TaskList to be stored
     */
    public void store(TaskList list) {
        boolean ignored = new File(savePath).getParentFile().mkdirs();
        try (FileOutputStream fw = new FileOutputStream(savePath); ObjectOutputStream out = new ObjectOutputStream(
                fw)) {
            out.writeObject(list);
        } catch (IOException e) {
            System.out.println("Saving error");
        }
    }

    /**
     * Loads the stored TaskList if it exists, else creates a new one.
     *
     * @return TaskList
     */
    public TaskList load() {
        try (FileInputStream fileInputStream = new FileInputStream(
                savePath); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (TaskList) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("No save file");
            return new TaskList();
        }
    }
}
