package duke;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    final String SAVE_FOLDER;
    final String SAVE_PATH;

    Storage(String SAVE_FOLDER) {
        this.SAVE_FOLDER = SAVE_FOLDER;
        SAVE_PATH = SAVE_FOLDER + "/save.txt";
    }

    /**
     * Stores the parameterized TaskList as a serialized file
     *
     * @param list TaskList to be stored
     */
    void store(TaskList list) {
        File dir = new File(SAVE_FOLDER);
        if (!dir.exists()) {
            boolean ignored = dir.mkdir();
        }
        try (FileOutputStream fw = new FileOutputStream(SAVE_PATH); ObjectOutputStream out = new ObjectOutputStream(
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
    TaskList load() {
        try (FileInputStream fileInputStream = new FileInputStream(
                SAVE_PATH); ObjectInputStream objectInputStream = new ObjectInputStream(
                fileInputStream)) {
            return (TaskList) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("No save file");
            return new TaskList();
        }
    }
}
