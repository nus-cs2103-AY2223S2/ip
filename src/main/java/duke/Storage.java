package duke;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    static String saveFolder = "data";
    static String savePath = saveFolder + "/save.txt";

    /**
     * Stores the parameterized TaskList as a serialized file
     *
     * @param list TaskList to be stored
     */
    static void store(TaskList list) {
        File dir = new File(saveFolder);
        if (!dir.exists()) {
            boolean ignored = dir.mkdir();
        }
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
    static TaskList load() {
        try (FileInputStream fileInputStream = new FileInputStream(
                savePath); ObjectInputStream objectInputStream = new ObjectInputStream(
                fileInputStream)) {
            return (TaskList) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("No save file");
            return new TaskList();
        }
    }
}
