package seedu.duke;

import java.io.*;
import java.util.ArrayList;


public class Storage {

    private static final File save = new File("./data/duke.txt");

    public static boolean saveExists() {
        return save.exists();
    }

    /**
     * Creates a new save file
     */
    public static void createSave() {
        try {
            save.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create new save file: " + e);
        }
    }

    /**
     * Creates a new TaskList based on an existing save
     * @param ls The TaskList to be written to
     */
    public static TaskList loadSave() {
        try {
            FileInputStream f = new FileInputStream(new File("./data/duke.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            TaskList loadedList = (TaskList) o.readObject();
            return loadedList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Saves the TaskList into a save file
     * @param ls The TaskList to be saved
     * @throws IOException When the save file cannot be written to
     */
    public static void saveList(TaskList ls) throws IOException {
        FileOutputStream f = new FileOutputStream(new File("./data/duke.txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(ls);
        o.close();
    }
}
