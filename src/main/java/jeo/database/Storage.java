package jeo.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import jeo.task.Deadline;
import jeo.task.Event;
import jeo.task.Task;
import jeo.task.ToDo;

/**
 * Represents the storage for loading and saving tasks.
 * @author Goh Jun How
 * @version 0.3
 */
public class Storage {
    protected static final String DATE_TIME_TO_PARSE = "yyyy-MM-dd HH:mm";
    protected String path;

    /**
     * Creates the storage object with the specified path to load tasks.
     * @param path String representing the file path.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads tasks that have been saved previously.
     * @return TaskList containing saved tasks.
     * @throws FileNotFoundException if file does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(path);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String taskString = sc.nextLine();
            Task currTask = parse(taskString);
            arr.add(currTask);
        }
        sc.close();
        return arr;
    }

    /**
     * Saves tasks to data file.
     * @param taskList TaskList containing tasks to be saved.
     * @throws IOException if data cannot be written to the text file.
     */
    public void save(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task task: taskList) {
            DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_TIME_TO_PARSE);
            StringBuilder sb = new StringBuilder(task.getStatusIcon())
                    .append("\\")
                    .append(task.getDescription())
                    .append("\\");
            if (task instanceof ToDo) {
                sb.append("T").append("\\").append(task.getTags());
                fw.write(sb + System.lineSeparator());
            } else if (task instanceof Deadline) {
                sb.append("D")
                        .append("\\")
                                .append(((Deadline) task).getDateTimeBy().format(formatterParse))
                                        .append("\\")
                                                .append(task.getTags());
                fw.write(sb + System.lineSeparator());
            } else {
                sb.append("E")
                        .append("\\")
                                .append(((Event) task).getDateTimeFrom().format(formatterParse))
                                        .append("\\")
                                                .append(((Event) task).getDateTimeTo().format(formatterParse))
                                                        .append("\\")
                                                                .append(task.getTags());
                fw.write(sb + System.lineSeparator());
            }

        }
        fw.close();
    }

    /**
     * Returns task corresponding to the accepted string.
     * @param str String representing formatted text to be parsed.
     * @return Task corresponding to the accepted string.
     */
    public Task parse(String str) {
        Task task = null;
        String[] arr = str.split("\\\\");
        switch (arr[2]) {
        case "T":
            if (arr.length == 3) {
                task = new ToDo(arr[1], "");
            } else {
                String[] tagArr = Arrays.copyOfRange(arr, 3, arr.length);
                task = new ToDo(arr[1], String.join("\\", tagArr));
            }
            break;
        case "D":
            if (arr.length == 4) {
                task = new Deadline(arr[1], arr[3], "");
            } else {
                String[] tagArr = Arrays.copyOfRange(arr, 4, arr.length);
                task = new Deadline(arr[1], arr[3], String.join("\\", tagArr));
            }
            break;
        case "E":
            if (arr.length == 5) {
                task = new Event(arr[1], arr[3], arr[4], "");
            } else {
                String[] tagArr = Arrays.copyOfRange(arr, 5, arr.length);
                task = new Event(arr[1], arr[3], arr[4], String.join("\\", tagArr));
            }
            break;
        default:
            throw new IllegalStateException();
        }
        if (arr[0].equals("X")) {
            task.markAsDone();
        }
        return task;
    }
}
