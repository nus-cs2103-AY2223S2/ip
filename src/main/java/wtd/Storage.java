package wtd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import wtd.exceptions.StorageException;
import wtd.task.Deadline;
import wtd.task.Event;
import wtd.task.Task;
import wtd.task.Todo;

/**
 * Storage class to handle saving and loading of tasks.
 */
public class Storage {
    /** File to save and load tasks from. */
    private File file;

    /**
     * Constructor for Storage class.
     * 
     * @param filePath the path of the file to save and load tasks from.
     * @throws WtdException
     */
    public Storage(String filePath) throws StorageException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new StorageException();
            }
        }
    }

    /**
     * Loads the tasks from the file.
     * 
     * @return the ArrayList of tasks.
     * @throws WtdException
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] split = s.nextLine().split(" \\| ", 5);
                switch(split[0]) {
                case "T":
                    list.add(new Todo(
                            split[2],
                            split[1].equals("1")
                    ));
                    break;
                case "D":
                    list.add(new Deadline(
                            split[2],
                            LocalDate.parse(split[3]),
                            split[1].equals("1")
                    ));
                    break;
                case "E":
                    list.add(new Event(
                            split[2],
                            LocalDate.parse(split[3]),
                            LocalDate.parse(split[4]),
                            split[1].equals("1")
                    ));
                    break;
                }
            }
            s.close();
        } catch (IOException e) {
            throw new StorageException();
        }
        return list;
    }

    /**
     * Saves the tasks to the file.
     * 
     * @param list the ArrayList of tasks to save.
     * @throws WtdException
     */
    public void save(TaskList list) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : list) {
                fw.write(task.toFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {

        }
    }
}
