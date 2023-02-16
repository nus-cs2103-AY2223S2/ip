package duke;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * This class is used to load and save the task list
 */
public class Storage {
    private File saveFile;
    /**
     * Constructor for Duke object.
     * Creating a new file with the filepath given.
     */
    public Storage(String filepath) {
        this.saveFile = new File(filepath);
    }
    /**
     * Loading the task list from the file.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        if (saveFile.exists()) {
            try {
                ObjectInputStream objin = new ObjectInputStream(new FileInputStream(saveFile));
                tasks = (TaskList) objin.readObject();
                objin.close();
            } catch (Exception e) {
                System.out.println("File is empty");
            }
            return tasks;
        } else {
            try {
                saveFile.createNewFile();
                throw new DukeException("unable to load save file");
            } catch (IOException e) {
                System.out.println("Cant create file");
            }
            return tasks;
        }
    }

    /**
     * It creates a new ObjectOutputStream object, which is a stream that writes objects to a file. It
     * then writes the TaskList object to the file, and closes the stream
     * @param tasks the TaskList object that is to be saved
     */
    public void saveTaskList(TaskList tasks) {
        try {
            ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(saveFile));
            objout.writeObject(tasks);
            objout.close();
        } catch (IOException e) {
            System.out.println("could not create save file");
        }
    }

}
