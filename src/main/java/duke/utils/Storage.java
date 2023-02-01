package duke.utils;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.Tasks.Task;

/**
 * A utility class that loads from an existing storage or creates a new storage file.
 */
public class Storage {
    private static DukeIO dukeIo = new DukeIO();
    private ArrayList<Task> storedTasks;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream o;

    /**
     * Reads byte stream from pre-existing .txt file.
     * 
     * @return storedTasks ArrayList that is loaded from exisiting storage.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        fis = new FileInputStream("./data/duke.txt");
        ois = new ObjectInputStream(fis);
        this.storedTasks = (ArrayList<Task>) ois.readObject();
        fis.close();
        ois.close();
        dukeIo.notifyLoad();
        return storedTasks; 
    }    

    /**
     * Writes ArrayList of tasks as a Serialized byte stream into a .txt file.
     * If .txt file exists, changes are overwritten.
     * If the directory or .txt file does not exist, the directory and a new .txt file is created.
     * 
     * @param tasks
     * @throws IOException
     */
    public void saveFrom(ArrayList<Task> tasks) throws IOException {
        // Only save if there are tasks to be saved in the ArrayList
        if (tasks.size() > 0) {
            /**
             * If 'data' directory does not exist,
             * create 'data' directory by creating allparent directories.
             */
            Files.createDirectories(Paths.get("./data/"));
            /** Creates a file ouput stream with the specified name if it exists
             * creates a regular file if it does not exist.
             * Data is written from 0-th offset. All changes are overwritten.
             */
            fos = new FileOutputStream("./data/duke.txt");
            o = new ObjectOutputStream(fos);
            o.writeObject(tasks);
            o.close();
            fos.close();
            dukeIo.notifySave();
        }
    }

}
