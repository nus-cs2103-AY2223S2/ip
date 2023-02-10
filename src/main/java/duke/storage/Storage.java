package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Tasks;
import duke.tasks.Todo;

/**
 * Represents a storage for data received in Duke.
 */
public class Storage {

    private static final String FILE_PATH = "data/DukeData.txt";
    private static final String DIRECTORY = "data";

    public Storage() {
        this.createDirectory();
    }

    /**
     * Creates the data directory if it does not exist.
     */
    public void createDirectory() {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Loads data from storage file into task list.
     * @param tasks the task list.
     * @throws IllegalStateException If task type is invalid.
     */
    public void load(Tasks tasks) throws IllegalStateException {
        try {
            File data = new File(FILE_PATH);
            //create file if file does not exist
            if (data.createNewFile()) {
                System.out.println("Hey hey~ Welcome new user~ :)");
                return;
            }
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                String currLine = s.nextLine();
                String[] details = currLine.split("\\|");
                String type = details[0];
                String status = details[1];
                String desc = details[2];
                switch (type) {
                case "T":
                    Todo t = new Todo(desc);
                    if (status.equalsIgnoreCase("X")) {
                        t.markTaskDone(true);
                    }
                    tasks.addToList(t, true);
                    break;
                case "D":
                    String by = details[3];
                    Deadline d = new Deadline(desc, by);
                    if (status.equalsIgnoreCase("X")) {
                        d.markTaskDone(true);
                    }
                    tasks.addToList(d, true);
                    break;
                case "E":
                    String from = details[3];
                    String to = details[4];
                    Event e = new Event(desc, from, to);
                    if (status.equalsIgnoreCase("X")) {
                        e.markTaskDone(true);
                    }
                    tasks.addToList(e, true);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
                }
            }
            s.close();
        } catch (IOException e) {
            System.out.println("Data file error.");
        }
    }

    /**
     * Saves tasks in task list into storage file.
     * @param tasks the task list.
     */
    public void save(Tasks tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            String data = tasks.formatForFile();
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}

