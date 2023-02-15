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
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNextLine()) {
            String[] parts = parseData(s.nextLine());
            String type = parts[0];
            String isDone = parts[1];
            addTask(tasks, type, isDone, parts);
        }
        return tasks;
    }

    private String[] parseData(String data) {
        return data.split(Pattern.quote(" | "));
    }

    /**
     * Adds a task to the Task List.
     *
     * @param tasks The array list of Tasks.
     * @param type The type of this current task (T/E/D).
     * @param isDone The status of Task.
     * @param inputs The String stored in storage file.
     */
    private void addTask(ArrayList<Task> tasks, String type, String isDone, String[] inputs) {
        String name = inputs[2];

        switch (type) {

        case "T":
            Todo todo = new Todo(name, Boolean.parseBoolean(isDone));
            tasks.add(todo);
            break;

        case "D":
            String by = inputs[3].substring(4);
            Deadline deadline = new Deadline(name, Boolean.parseBoolean(isDone), by, FORMAT);
            tasks.add(deadline);
            break;

        case "E":
            String from = inputs[3].substring(6);
            String to = inputs[4].substring(4);
            Event event = new Event(name, Boolean.parseBoolean(isDone), from, to, FORMAT);
            tasks.add(event);
            break;

        default:
            // Do Nothing
        }
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

