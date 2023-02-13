package dude.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.TaskList;
import dude.task.Todo;

/**
 * Handles file data input and output.
 */
public class Storage {

    private final String filePath;

    /**
     * Initializes Storage.
     *
     * @param filePath Path to the data file stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        checkFile(filePath);
    }

    /**
     * Checks if file path provided is valid.
     * If not valid, creates the directory and file.
     *
     * @param filePath Path to the data file stored.
     */
    private void checkFile(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                File d = new File("data");
                if (!d.exists()) {
                    d.mkdir();
                }
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Converts TaskList provided into raw data and save it by writing into file.
     *
     * @param tasks TaskList to be converted into raw data.
     */
    public void saveData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String rawData = tasks.toRaw();
            fw.write(rawData);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads raw data stored in the file as TaskList.
     *
     * @return TaskList which stores the data loaded from file.
     */
    public ArrayList<Task> loadData() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);
        if (f.exists()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] output = sc.nextLine().split(" \\| ");
                Task task = null;
                switch (output[0]) {
                case "T":
                    task = new Todo(output[2]);
                    break;
                case "D":
                    task = new Deadline(output[2], output[3]);
                    break;
                case "E":
                    task = new Event(output[2], output[3], output[4]);
                    break;
                default:
                    break;
                }

                if (output[1].equals("1")) {
                    task.mark();
                }

                taskList.add(task);
                Task.addTaskCount();
            }
        }
        return taskList;
    }
}
