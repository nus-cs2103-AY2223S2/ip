package duke;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class Save {
    /**
     * Initliaze ArrayList of Task.
     * if file "save" does not exist, create it and return empty ArrayList of Task
     * else load contentes of "save" into the ArrayList of Task
     * @return ArrayList of Task
     */
    public static ArrayList<Task> loadSave() {
        File file = new File("./save");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream("./save");
                    ObjectInputStream ois = new ObjectInputStream(fis)
            ) {
                @SuppressWarnings("unchecked")
                ArrayList<Task> tasks = (ArrayList<Task>) ois.readObject();
                return tasks;
            } catch (IOException exception) {
                System.out.println("Wrong format object");
            } catch (ClassNotFoundException exception) {
                System.out.println("object can't be loaded");
            }
            return new ArrayList<>();
        } else {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                System.out.println("cant create new file");
            }
            return new ArrayList<>();
        }
    }

    /**
     * Save current state of ArrayList object into file "save".
     * @param tasks ArrayList of Task to be saved
     */
    public static void makeSave(ArrayList<Task> tasks) {
        try (FileOutputStream fos = new FileOutputStream("./save");
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(tasks);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
