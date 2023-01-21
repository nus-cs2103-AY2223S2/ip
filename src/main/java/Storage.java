import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This storage class represents Duke's storage.
 */
public class Storage {
    private File store;

    public Storage(File f) {
        store = f;
    }

    /**
     * Saves the tasks to the storage file.
     *
     * @param tasks The tasks to save.
     */
    public void saveToFile(ArrayList<Task> tasks) {
        try {
            FileOutputStream temp = new FileOutputStream(store);
            ObjectOutputStream out = new ObjectOutputStream(temp);

            out.writeObject(tasks);

            out.close();
            temp.close();

            System.out.println("Success!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads from the storage file.
     *
     * @return The arraylist containing the tasks.
     */
    public ArrayList<Task> loadFromFile() {
        try {
            FileInputStream temp = new FileInputStream(store);
            ObjectInputStream in = new ObjectInputStream(temp);

            // Any corruption will be handled later.
            @SuppressWarnings("unchecked")
            ArrayList<Task> output = (ArrayList<Task>) in.readObject();

            in.close();
            temp.close();

            return output;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
