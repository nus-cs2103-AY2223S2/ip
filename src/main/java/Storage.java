package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Represents memory of Duke. Contains methods that help Duke recover previously save task list and save task list
 * before closing programme.
 */
public class Storage {

    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");
    private String filePath;

    /**
     * Initialises new instance of Storage.
     *
     * @param filePath The file path to the file where data is stored and retrieved from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads string data of tasks from file and organise the tasks into an ArrayList.
     *
     * @return ArrayList An ArrayList containing all the tasks previously stored in memory.
     * @throws FileNotFoundException Filepath is invalid.
     */
    public ArrayList<Task> loadData() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> arr = new ArrayList<>();
        while (s.hasNextLine()) {
            String[] parts = s.nextLine().split(Pattern.quote(" | "));
            switch (parts[0]) {

            case "T":
                arr.add(new Todo(parts[2], Boolean.parseBoolean(parts[1])));
                break;

            case "D":
                arr.add(new Deadline(parts[2], Boolean.parseBoolean(parts[1]), parts[3].substring(4), FORMAT));
                break;

            case "E":
                arr.add(new Event(parts[2], Boolean.parseBoolean(parts[1]), parts[3].substring(6),
                        parts[4].substring(4), FORMAT));
                break;

            default:
                throw new FileNotFoundException();
            }
        }
        return arr;
    }

    /**
     * Saves ArrayList of task into file as a string.
     *
     * @param tasks List of task to be stored in memory.
     * @throws IOException Filepath is invalid.
     */
    public void saveData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        fw.write(tasks.toString());
        fw.close();
    }

}

