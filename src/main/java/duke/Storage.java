package duke;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The class to store tasks into a text file.
 */
public class Storage {
    private String filePath;

    /**
     * Storage constructor.
     *
     * @param filePath The relative path of the text file that tasks are saved into.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the TaskList into the text file.
     * Prints the error message if there is one.
     *
     * @param tl TaskList that contains objects of type Task.
     */
    public void saveTaskList(TaskList tl) {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tl);
            oos.close();
            fos.close();
            System.out.println("Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the TaskList from the text file into the program.
     * If file exists, return the TaskList stored in the file.
     * If not, return a new TaskList and creates the file.
     *
     * @return TaskList
     */
    public TaskList loadTaskList() {
        File file = new File(this.filePath);
        if (file.exists()) {
            TaskList tl = new TaskList();
            try {
                FileInputStream fis = new FileInputStream(this.filePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                tl = (TaskList) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                System.out.println("File is empty");
            }
            return tl;
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Cant create file");
            }
            return new TaskList();
        }
    }
}
