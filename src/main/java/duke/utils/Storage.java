package duke.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * A utility class that loads from an existing storage or creates a new .txt
 * file to store user's session.
 */
public class Storage {
    private static DukeIo dukeIo = new DukeIo();
    private ArrayList<Task> storedTasks;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream o;

    /**
     * Reads byte stream from pre-existing .txt file.
     * @return storedTasks ArrayList that is loaded from existing storage.
     * @throws IOException Occurs due to errors in standard input and standard output.
     * @throws ClassNotFoundException Occurs when Task class is not read.
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
     * @param tasks List of tasks to be saved.
     * @throws IOException Occurs when there are errors writing stream to output.
     */
    public void saveFrom(ArrayList<Task> tasks) throws IOException {
        // Only save if there are tasks in the ArrayList task
        if (tasks.size() > 0) {
            /*
             * Creates 'data' directory by creating all parent directories first
             * if it does not exist.
             */
            Files.createDirectories(Paths.get("./data/"));
            /*
             * Creates a file output stream with the specified name if it exists or
             * creates a regular file if it does not exist.
             * Data is written from 0 offset, thereby overwritting previous saves.
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
